package basics;

import javax.persistence.Entity;
import java.text.ParseException;

@Entity
public class Release extends BaseEntity{

    private String title;
    private boolean official;
    private String language;
    private PartialDate releaseDate;
    private String format;
    private int trackCount;

    // Default constructor is necessary for gson to construct the object (otherwise it defaults to dirty UnsafeAllocator hacks)
    public Release() {
    }

    public Release(String title, boolean official, String language, String strReleaseDate, String format,
                   int trackCount) throws ParseException {
        this.title = title;
        this.official = official;
        this.language = language;
        this.releaseDate = PartialDate.parse(strReleaseDate);
        this.format = format;
        this.trackCount = trackCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public PartialDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) throws ParseException {
        this.releaseDate = PartialDate.parse(releaseDate);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    @Override
    public String toString() {
        return super.toString() + " " + title + " " + language;
    }
}
