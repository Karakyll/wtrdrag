package project.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import project.entity.Race;
import project.service.CarService;
import project.service.TrackService;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class RaceDeserializer extends StdDeserializer<Race> {

    @Autowired
    TrackService trackService;

    @Autowired
    CarService carService;

    public RaceDeserializer() {
        this(null);
    }

    public RaceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Race deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException, JsonProcessingException{

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Race result = new Race();

        try {
            result.setTrack(trackService.findTrackById(node.get("trackId").longValue()));
            result.setCar(carService.findCarById(node.get("carId").longValue()).get());
            result.setDateTime(new SimpleDateFormat("dd.MM.yyyy hh:mm").parse(node.get("dateTime").asText().replace('-', '.')));
            result.setReactionTime(node.get("reactionTime").asDouble());
            result.setElapsedTime(node.get("elapsedTime").asDouble());
            result.setFinishSpeed(node.get("finishSpeed").asDouble());
        } catch (java.text.ParseException e){ }

        return result;
    }

}
