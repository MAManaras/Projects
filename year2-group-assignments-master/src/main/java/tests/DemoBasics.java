package tests;

import basics.Album;
import basics.Compilation;
import basics.Group;
import basics.Person;

import java.text.ParseException;

public class DemoBasics {

    public static void main(String[] args) {
        try {
            Person mason = new Person("d6c37074-0155-46a3-8af2-338a4f625afc", "Nick Mason", "UK", Person.Gender.MALE, "1944-01-27", null);
            Person berrett = new Person("12327d75-47d5-45d9-84c2-3760b9210c17", "Syd Berrett", "UK", Person.Gender.MALE, "1946-01-06", "2006-07-07");
            Person wright = new Person("6f7e36da-79d8-4219-990d-8e9224d04ebc", "Richard Wright", "UK", Person.Gender.MALE, "1943-07-28", "2008-09-15");
            Person waters = new Person("0f50beab-d77d-4f0f-ac26-0b87d3e9b11b", "Roger Waters", "UK", Person.Gender.MALE, "1943-09-06", null);
            Person gilmour = new Person("1dce970e-34bc-48b2-ab51-48d87544a4c2", "David Gilmour", "UK", Person.Gender.MALE, "1946-03-06", null);

            Group floyd = new Group("83d91898-7763-47d7-b03b-b92132375c47", "Pink Floyd", "UK", "1965", "2014", mason, berrett, wright, waters, gilmour);

            Album tdsotm = new Album("The Dark Side of the Moon", true, "EN", "1973-03-24", "12'' Vinyl", 10, floyd);

            Person bowie = new Person("5441c29d-3602-4898-b1a1-b77fa23b8e50", "David Bowie", "UK", Person.Gender.MALE, "1947-01-08", "2016-01-10");
            Album blackstar = new Album("★", true, "EN", "2016-01-08", "CD", 7, bowie);

            Compilation comp = new Compilation("Test compilation", false, "EN", "2019-01", "CD", 10, bowie, floyd);
        } catch (ParseException e) {
            e.printStackTrace(); // ¯\_(ツ)_/¯ Should never happen (famous last words)
        }

    }
}