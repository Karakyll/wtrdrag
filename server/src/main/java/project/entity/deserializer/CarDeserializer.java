package project.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import org.springframework.beans.factory.annotation.Autowired;
import project.entity.Car;
import project.service.SponsorService;

import java.io.IOException;

/**
 * Class deserializer for "Car" entity
 */
public class CarDeserializer extends StdDeserializer<Car> {

    @Autowired
    SponsorService sponsorService;

    public CarDeserializer() {
        this(null);
    }

    public CarDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Car deserialize(JsonParser jsonParser, DeserializationContext context)
        throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Car.CarBuilder carBuilder = new Car.CarBuilder(node.get("mark").asText(), node.get("model").asText());

        if (node.has("carId")) {
            carBuilder.carId((Long) ((LongNode) node.get("carId")).longValue());
        }

        if (node.has("pilotFirstName")) {
            carBuilder.pilotFirstName(node.get("pilotFirstName").asText());
        }

        if (node.has("pilotLastName")) {
            carBuilder.pilotLastName(node.get("pilotLastName").asText());
        }

        if (node.has("power")) {
            carBuilder.power((Integer) ((IntNode) node.get("power")).numberValue());
        }

        if (node.has("torque")) {
            carBuilder.torque((Integer) ((IntNode) node.get("torque")).numberValue());
        }

        if (node.has("spec")) {
            carBuilder.spec(node.get("spec").asText());
        }

        if (node.has("sponsorId")) {
            carBuilder.sponsor(sponsorService.findSponsorById((Long) ((NumericNode) node.get("sponsorId")).longValue()));
        }

        return carBuilder.build();
    }

}
