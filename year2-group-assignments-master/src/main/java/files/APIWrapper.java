package files;

import basics.*;
import com.google.gson.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

public class APIWrapper {

    private static final String endpoint = "https://musicbrainz.org/ws/2/";
    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(PartialDate.class, new PartialDateTypeAdapter())
            .registerTypeHierarchyAdapter(Artist.class, new ArtistMusicbrainzDeserializer())
            .registerTypeHierarchyAdapter(Release.class, new ReleaseMusicbrainzDeserializer())
            .create();
    private static JsonParser parser = new JsonParser();

    private static JsonElement makeRequest(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        Reader reader = new InputStreamReader(url.openConnection().getInputStream());
        JsonElement result = parser.parse(reader);
        reader.close();

        return result;
    }

    private static JsonObject getEntityFromId(Type type, String id) throws IOException {
        return makeRequest(endpoint + type.getName() + "/" + id + "?inc=tags&fmt=json").getAsJsonObject();
    }

    /**
     * Make a plain search on Musicbrainz and return the result in JSON. All parameters supported by musicbrainz can be used (Lucene filters)
     *
     * @param query The query to send
     * @param type  The entity type to search for
     * @return The response in JSON
     */
    public static JsonObject search(String query, Type type) throws IOException {
        return makeRequest(endpoint + type.getName()
                + "?fmt=json&query=" + URLEncoder.encode(query, StandardCharsets.UTF_8.displayName()))
                .getAsJsonObject();
    }

    /**
     * Search Musicbrainz for an artist with a specific name
     *
     * @param artist The name of the artist
     * @return A list of the results
     */
    public static List<Artist> getArtists(String artist) throws IOException {
        JsonObject response = search("sortname:\"" + artist.replace("\"", "") + "\"", Type.ARTIST);
        List<Artist> artists =  Arrays.asList(gson.fromJson(response.getAsJsonArray("artists"), Artist[].class));
        artists.forEach(a -> a.setFullyRetrieved(true));
        return artists;
    }

    /**
     * Search musicbrainz for an artist from a specific country
     *
     * @param artistname The artist name to search for
     * @param country    The country to use as a filter
     * @return A list of the results
     */
    public static List<Artist> getArtistsFromCountry(String artistname, String country) throws IOException {
        JsonObject response = search("sortname:" + artistname.replace("\"", "") + " AND country:" + country, Type.ARTIST);

        List<Artist> artists =  Arrays.asList(gson.fromJson(response.getAsJsonArray("artists"), Artist[].class));
        artists.forEach(a -> a.setFullyRetrieved(true));
        return artists;
    }

    /**
     * Search Musicbrainz for a release with a specific name
     *
     * @param release The name of the release
     * @return A list of the results
     */
    public static List<Release> getReleases(String release) throws IOException {
        JsonObject response = search("sortname:\"" + release.replace("\"", "") + "\"", Type.RELEASE);
        List<Release> releases =  Arrays.asList(gson.fromJson(response.getAsJsonArray("releases"), Release[].class));
        releases.forEach(r -> r.setFullyRetrieved(true));
        return releases;
    }

    /**
     * Get an artist from its ID
     * <p>
     * Can be used to gather additional information about an artist as a lot of times musicbrainz returns incomplete
     * results when it references an artist.
     *
     * @param id The ID of the artist
     * @return A constructed Artist object
     */
    public static Artist getArtistFromId(String id) throws IOException {
        Artist artist = gson.fromJson(getEntityFromId(Type.ARTIST, id), Artist.class);
        artist.setFullyRetrieved(true);
        artist.setLastUpdated(Instant.now());
        return artist;
    }

    /**
     * Get a release from its ID
     *
     * @param id The ID of the release
     * @return A constructed Release object
     */
    public static Release getReleaseFromId(String id) throws IOException {
        Release release =  gson.fromJson(getEntityFromId(Type.RELEASE, id), Release.class);
        release.setFullyRetrieved(true);
        release.setLastUpdated(Instant.now());
        return release;
    }

    /**
     * Query the list of members from a group
     *
     * @param group The group to use for the lookup
     * @return A list of members belonging to that group
     */
    public static List<Artist> getGroupMembers(Group group) throws IOException {
        if (group.getId() == null || group.getId().isEmpty()) {
            return null;
        }
        JsonObject response = makeRequest(endpoint + Type.ARTIST.getName() + "/" + group.getId() + "?fmt=json&inc=artist-rels").getAsJsonObject();
        if (!response.has("relations"))
            return null;

        ArrayList<Artist> members = new ArrayList<>();
        Set<String> idsSeen = new HashSet<>();
        for (JsonElement element : response.getAsJsonArray("relations")) {
            JsonObject rel = element.getAsJsonObject();

            if (rel.has("type") && rel.get("type").getAsString().equals("member of band")
                    && rel.has("artist")) {
                Artist artist = gson.fromJson(rel.getAsJsonObject("artist"), Artist.class);

                // musicbrainz returns duplicate results for some members, deduplicate to avoid issues down the line
                if (!idsSeen.contains(artist.getId())) {
                    members.add(artist);
                    idsSeen.add(artist.getId());
                }
            }

        }

        return members;
    }

    public enum Type {
        ARTIST("artist"),
        RELEASE("release"),
        RELEASE_GROUP("release-group"),
        RECORDING("recording"),
        WORK("work"),
        LABEL("label");

        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
