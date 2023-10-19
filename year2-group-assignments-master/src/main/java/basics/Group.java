package basics;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.text.ParseException;
import java.util.*;

@Entity
public class Group extends Artist {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Artist> members = new ArrayList<>();

    public Group() {
    }

    public Group(String id, String name, String country, String strBeginDate, String strEndDate,
                 Person... members) throws ParseException {
        super(id, name, country, strBeginDate, strEndDate);
        this.members.addAll(Arrays.asList(members));
    }

    /**
     * @return An unmodifiable List with all the members
     */
    public List<Artist> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public void addMember(Artist member) {
        members.add(member);
    }

    public void addAllMembers(List<Artist> person) {
        members.addAll(person);
    }

    public boolean removeMember(Artist member) {
        return members.remove(member);
    }

    public void setMember(int i, Artist artist) {
        members.set(i, artist);
    }
}
