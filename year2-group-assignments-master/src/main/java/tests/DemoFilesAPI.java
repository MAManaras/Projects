package tests;

import basics.Artist;
import basics.Group;
import basics.Release;
import files.APIWrapper;
import files.FileWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemoFilesAPI {
    public static void main(String[] args) throws IOException {

        List<Artist> artists = APIWrapper.getArtistsFromCountry("Pink Floyd", "UK");

        List<Artist> bowie = APIWrapper.getArtists("Bowie");

        if (artists.get(0) instanceof Group) {
            Group group = (Group) artists.get(0);
            group.addAllMembers(APIWrapper.getGroupMembers(group));
        }

        // Re-create the list because the result of get functions in APIWrapper are unmodifiable.
        artists = new ArrayList<>(artists);
        artists.add(bowie.get(0));

        Artist anotherArtist = APIWrapper.getArtistFromId("d8df7087-06d5-4545-9024-831bb8558ad1");
        artists.add(anotherArtist);

        FileWrapper.writeObjectToFile("groups.json", artists);
        List<Artist> list = FileWrapper.readArtistsFromFile("groups.json");

        List<Release> releases = APIWrapper.getReleases("Floodland");

        Release anotherRelease = APIWrapper.getReleaseFromId("6d749edd-d94c-47b4-88ab-166b865c7b89");
        releases = new ArrayList<>(releases);
        releases.add(anotherRelease);

        FileWrapper.writeObjectToFile("releases.txt", releases);
        List<Release> relList = FileWrapper.readAReleasesFromFile("releases.txt");

        System.out.println("Hello World");
    }
}
