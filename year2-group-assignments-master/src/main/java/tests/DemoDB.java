package tests;

import basics.Artist;
import basics.Group;
import basics.Release;
import db.Database;
import files.APIWrapper;

import java.util.List;

public class DemoDB {

    public static void main(String[] args) throws Exception {
        Database db = new Database("oracle12c.hua.gr/orcl", "username", "password");

        // Buckle up, this is gonna take a while with all the delays due to rate limiting.
        List<Artist> artists = APIWrapper.getArtists("Metallica");
        if (artists.get(0) instanceof Group) {
            Group group = (Group) artists.get(0);
            group.addAllMembers(APIWrapper.getGroupMembers(group));

            Thread.sleep(1000);
            for (int i = 0; i < group.getMembers().size(); i++) {
                Artist member = group.getMembers().get(i);
                Artist newArtist = APIWrapper.getArtistFromId(member.getId());
                group.setMember(i, newArtist);
                Thread.sleep(1000);
            }
        }
        Thread.sleep(1000);
        List<Artist> artists2 = APIWrapper.getArtists("Pink");
        Thread.sleep(1000);
        List<Artist> artists3 = APIWrapper.getArtists("Beatles");
        Thread.sleep(1000);
        List<Artist> artists4 = APIWrapper.getArtists("Sabaton");
        Thread.sleep(1000);
        List<Release> releases = APIWrapper.getReleases("Floodland");

        db.saveAll(artists);
        db.saveAll(artists2);
        db.saveAll(artists3);
        db.saveAll(artists4);
        db.saveAll(releases);

        List<Artist> results = db.searchArtistsByName("Metallica");
        List<Artist> results2 = db.searchArtistByTags("metal");
        List<Release> results3 = db.searchReleasesByTitle("Floodland");

        db.close();
    }
}
