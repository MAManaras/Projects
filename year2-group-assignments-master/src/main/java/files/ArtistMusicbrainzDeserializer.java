package files;

import basics.Artist;
import basics.Group;
import basics.Person;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;

public class ArtistMusicbrainzDeserializer implements JsonDeserializer<Artist> {

    private String getStringIfExists(JsonObject object, String elem) {
        if (object.has(elem) && !object.get(elem).isJsonNull()) {
            return object.get(elem).getAsString();
        }
        return null;
    }

    @Override
    public Artist deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();

        Artist artist;
        String type = getStringIfExists(json, "type");

        if (type == null) {
            artist = new Artist();
        } else if (type.equals("Group")) {
            artist = new Group();
        } else if (type.equals("Person")) {
            artist = new Person();
        } else {
            System.err.println("Unknown artist type: " + type);
            artist = new Artist();
        }

        artist.setId(getStringIfExists(json, "id"));
        artist.setName(getStringIfExists(json, "name"));
        artist.setCountry(getStringIfExists(json, "country"));

        JsonObject life = json.getAsJsonObject("life-span");
        if (life != null) {
            try {
                artist.setBeginDate(getStringIfExists(life, "begin"));
                artist.setEndDate(getStringIfExists(life, "end"));
            } catch (ParseException e) {
                // Not much we can do other than that :(
                e.printStackTrace();
            }
        }

        JsonArray tags = json.getAsJsonArray("tags");
        if (tags != null) {
            tags.forEach(t -> artist.addTag(t.getAsJsonObject().get("name").getAsString()));
        }

        JsonArray aliases = json.getAsJsonArray("aliases");
        if (aliases != null) {
            aliases.forEach(t -> artist.addAlias(t.getAsJsonObject().get("name").getAsString()));
        }

        if (artist instanceof Person && json.has("gender")) {
            String strGender = getStringIfExists(json, "gender");
            if (strGender != null)
                ((Person) artist).setGender(Person.Gender.valueOf(strGender.toUpperCase()));
        }

        return artist;
    }
}
