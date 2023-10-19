package files;

import basics.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileWrapper {

    private static Gson gson;

    static {
        RuntimeTypeAdapterFactory<Artist> artistAdapterFactory = RuntimeTypeAdapterFactory.of(Artist.class)
                .registerSubtype(Artist.class)
                .registerSubtype(Group.class)
                .registerSubtype(Person.class);

        RuntimeTypeAdapterFactory<Release> releaseAdapterFactory = RuntimeTypeAdapterFactory.of(Release.class)
                .registerSubtype(Release.class)
                .registerSubtype(Album.class)
                .registerSubtype(Compilation.class);

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(PartialDate.class, new PartialDateTypeAdapter())
                .registerTypeAdapterFactory(artistAdapterFactory)
                .registerTypeAdapterFactory(releaseAdapterFactory)
                .create();
    }

    /**
     * Write an object to a file
     *
     * @param filename The filename to use
     * @param object   The object to be written
     */
    public static void writeObjectToFile(String filename, Object object) throws IOException {
        FileWriter out = new FileWriter(filename);
        gson.toJson(object, out);
        out.flush();
        out.close();
    }

    /**
     * Read artist objects from a JSON file
     *
     * @param filename The filename to use
     * @return The list of artists that was read.
     */
    public static List<Artist> readArtistsFromFile(String filename) throws IOException {
        FileReader reader = new FileReader(filename);

        List<Artist> artists = Arrays.asList(gson.fromJson(reader, Artist[].class));

        reader.close();
        return artists;
    }

    /**
     * Read release objects from a JSON file
     *
     * @param filename The filename to use
     * @return The list of releases that was read.
     */
    public static List<Release> readAReleasesFromFile(String filename) throws IOException {
        FileReader reader = new FileReader(filename);

        List<Release> release = Arrays.asList(gson.fromJson(reader, Release[].class));

        reader.close();
        return release;
    }

}
