package basics;

import org.jboss.logging.Field;

import javax.persistence.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Artist extends BaseEntity {

    private String name;
    private String country;

    @Embedded
    private PartialDate beginDate;
    @Embedded
    private PartialDate endDate;

    @ElementCollection
    private List<String> cities = new ArrayList<>();
    @ElementCollection
    private List<String> aliases = new ArrayList<>();
    @ElementCollection
    private List<String> tags = new ArrayList<>();

    // Default constructor is necessary for gson to construct the object (otherwise it defaults to dirty UnsafeAllocator hacks)
    public Artist() {
    }

    public Artist(String id, String name, String country, String strBeginDate, String strEndDate) throws ParseException {
        super(id);
        this.name = name;
        this.country = country;
        this.beginDate = PartialDate.parse(strBeginDate);
        this.endDate = PartialDate.parse(strEndDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PartialDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) throws ParseException {
        this.beginDate = PartialDate.parse(beginDate);
    }

    public PartialDate getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) throws ParseException {
        this.endDate = PartialDate.parse(endDate);
    }

    /**
     * @return An unmodifiable list of the cities
     */
    public List<String> getCities() {
        return Collections.unmodifiableList(cities);
    }

    public void addCity(String city) {
        cities.add(city);
    }

    public boolean removeCity(String city) {
        return cities.remove(city);
    }

    /**
     * @return An unmodifiable list of the aliases
     */
    public List<String> getAliases() {
        return Collections.unmodifiableList(aliases);
    }

    public void addAlias(String alias) {
        aliases.add(alias);
    }

    public boolean removeAlias(String alias) {
        return aliases.remove(alias);
    }

    /**
     * @return An unmodifiable list of the tags
     */
    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    @Override
    public String toString() {
        return super.toString() + " " + name + (country != null ?  " " + country : "");
    }
}