package basics;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.text.ParseException;

@Entity
public class Album extends Release {

    @ManyToOne
    private Artist artist;

    public Album() {
    }

    public Album(String title, boolean official, String language, String strReleaseDate, String format, int trackCount,
                 Artist artist) throws ParseException {
        super(title, official, language, strReleaseDate, format, trackCount);
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
