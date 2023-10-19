package basics;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
public class Compilation extends Release {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Artist> artists = new ArrayList<>();

    public Compilation() {
    }

    public Compilation(String title, boolean official, String language, String strReleaseDate, String format,
                       int trackCount, Artist... artists) throws ParseException {
        super(title, official, language, strReleaseDate, format, trackCount);
        this.artists.addAll(Arrays.asList(artists));
    }

    /**
     * @return An unmodifiable list of the artists
     */
    public List<Artist> getArtists() {
        return Collections.unmodifiableList(artists);
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public void addAllArtists(List<Artist> artists) {
        this.artists.addAll(artists);
    }

    public boolean removeArtist(Artist artist) {
        return artists.remove(artist);
    }
}
