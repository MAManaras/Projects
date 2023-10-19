package files;

import basics.Album;
import basics.Artist;
import basics.Compilation;
import basics.Release;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;

public class ReleaseMusicbrainzDeserializer implements JsonDeserializer<Release> {

    private String getStringIfExists(JsonObject object, String elem) {
        if (object.has(elem) && !object.get(elem).isJsonNull()) {
            return object.get(elem).getAsString();
        }
        return null;
    }

    @Override
    public Release deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();

        String type = null;
        if (json.has("release-group")) {
            JsonObject releaseGroup = json.getAsJsonObject("release-group");
            if (releaseGroup.has("primary-type"))
                type = releaseGroup.get("primary-type").getAsString();

            // Compilation is a secondary type :/
            // Some releases are "Album + Compilation" so we intentionally override it after checking the primary type.
            if (releaseGroup.has("secondary-type")) {
                for (JsonElement elem : releaseGroup.getAsJsonArray("secondary-type")) {
                    String secondaryType = elem.getAsString();
                    if (secondaryType.equals("Compilation")) {
                        type = secondaryType;
                        break;
                    }
                }
            }
        }

        Release release;
        if (type == null) {
            release = new Release();
        } else if (type.equals("Album")) {
            release = new Album();
        } else if (type.equals("Compilation")) {
            release = new Compilation();
        } else {
            System.err.println("Unknown release type: " + type);
            release = new Release();
        }

        release.setId(getStringIfExists(json, "id"));
        release.setTitle(getStringIfExists(json, "title"));

        if (json.has("text-representation")) {
            release.setLanguage(getStringIfExists(json.getAsJsonObject("text-representation"), "language"));
        }

        try {
            release.setReleaseDate(getStringIfExists(json, "date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (json.has("media") && json.getAsJsonArray("media").size() > 0) {
            JsonObject medium = json.getAsJsonArray("media").get(0).getAsJsonObject();
            release.setFormat(getStringIfExists(medium, "format"));
            if (medium.has("track-count"))
                release.setTrackCount(medium.get("track-count").getAsInt());
        }

        if (json.has("status")) {
            release.setOfficial(json.get("status").getAsString().equals("Official"));
        }

        if (json.has("artist-credit")
                && json.getAsJsonArray("artist-credit").size() > 0) {
            JsonArray jsonArtists = new JsonArray();

            for (JsonElement element : json.getAsJsonArray("artist-credit")) {
                JsonObject jart = element.getAsJsonObject();
                if (jart.has("artist")) {
                    jsonArtists.add(jart.get("artist"));
                }
            }

            if (release instanceof Album) {
                Artist artist = context.deserialize(jsonArtists.get(0), Artist.class);
                ((Album) release).setArtist(artist);
            } else if (release instanceof Compilation) {
                List<Artist> artists = context.deserialize(jsonArtists, Artist[].class);
                ((Compilation) release).addAllArtists(artists);
            }
        }

        return release;
    }

}
