package files;

import basics.PartialDate;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;

public class PartialDateTypeAdapter extends TypeAdapter {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        if (value == null || value.getClass() != PartialDate.class) {
            out.nullValue();
            return;
        }

        PartialDate date = (PartialDate) value;
        out.value(date.getIsoDate());
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        String date = in.nextString();
        try {
            return PartialDate.parse(date);
        } catch (ParseException e) {
            throw new JsonParseException("Couldn't parse PartialDate" + date);
        }
    }
}
