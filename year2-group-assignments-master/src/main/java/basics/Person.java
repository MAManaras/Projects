package basics;

import javax.persistence.Entity;
import java.text.ParseException;

@Entity
public class Person extends Artist {

    public enum Gender {
        MALE,
        FEMALE
    }

    private Gender gender;

    public Person() {
    }

    public Person(String id, String name, String country, Gender gender, String strBirthDate,
                  String strDeathDate) throws ParseException {
        super(id, name, country, strBirthDate, strDeathDate);
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
