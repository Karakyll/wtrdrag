package project.entity.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import project.entity.Race;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Class serializer for "Race" entity
 */
public class RaceSerializer extends JsonSerializer<Race> {

    @Override
    public void serialize(Race race, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("raceId", race.getRaceId());
        jsonGenerator.writeNumberField("carId", race.getCar().getCarId());
        jsonGenerator.writeNumberField("trackId", race.getTrack().getTrackId());
        jsonGenerator.writeStringField("dateTime", new SimpleDateFormat("dd.MM.yyyy hh:mm").format(race.getDateTime()));
        jsonGenerator.writeNumberField("reactionTime", race.getReactionTime());
        jsonGenerator.writeNumberField("elapsedTime", race.getElapsedTime());
        jsonGenerator.writeNumberField("finishSpeed", race.getFinishSpeed());
        jsonGenerator.writeEndObject();

    }

}
