package basics;

import javax.persistence.Embeddable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Dates on Musicbrainz aren't always complete. Take Pink Floyd[1] for example. The begin/end dates contain only the year,
 * while for others it also contains the month or even the full date. As a result, the built-in java DateFormatter
 * classes won't cut it, so we have to implement our own wrapper around them.
 *
 * [1] https://musicbrainz.org/artist/83d91898-7763-47d7-b03b-b92132375c47
 */

/**
 * A wrapper around LocalDate adding support for partial accuracy.
 */
@Embeddable
public class PartialDate {

    private static final String ISO_DATE = "1970-01-01";

    // If you can't extend them, wrap 'em
    private LocalDate localDate;

    public enum Accuracy {
        YEAR("YYYY"),
        MONTH("YYYY-MM"),
        DAY("YYYY-MM-DD");

        String format;

        Accuracy(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    private Accuracy accuracy;

    private PartialDate() {}

    private PartialDate(LocalDate localDate, Accuracy accuracy) {
        this.localDate = localDate;
        this.accuracy = accuracy;
    }

    /**
     * Parse an ISO Date into a PartialDate object. The following formats are supported: YYYY, YYYY-MM, YYYY-MM-DD, any
     * other format will result in a ParseException
     *
     * @param date The string to parse
     * @return A new PartialDate object
     * @throws ParseException In case the provided string fails to validate
     */
    public static PartialDate parse(String date) throws ParseException {
        if (date == null || date.isEmpty())
            return null;

        Accuracy accuracy;
        switch (date.length()) { // YYYY-MM-DD
            case 4:
                accuracy = Accuracy.YEAR;
                break;
            case 7:
                accuracy = Accuracy.MONTH;
                break;
            case 10:
                accuracy = Accuracy.DAY;
                break;
            default:
                throw new ParseException("Couldn't parse " + date + " as an ISO date", 0);
        }

        String fullDate = date + ISO_DATE.substring(date.length());

        return new PartialDate(LocalDate.parse(fullDate), accuracy);
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    /**
     * Mind the accuracy field when using this, anything more specific than the accuracy holds no meaning and can be any
     * garbage value.
     *
     * @return The LocalDate object associated with this PartialDate.
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getIsoDate() {
        return localDate.format(DateTimeFormatter.ISO_DATE).substring(0, accuracy.format.length());
    }

    @Override
    public String toString() {
        return localDate.toString();
    }
}
