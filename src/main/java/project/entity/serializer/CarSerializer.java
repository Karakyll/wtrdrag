package project.entity.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import project.entity.Car;

import java.io.IOException;

/**
 * Class serializer for "car" entity
 */
public class CarSerializer extends JsonSerializer<Car> {

    @Override
    public void serialize(Car car, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("carId", car.getCarId());
        jsonGenerator.writeStringField("mark", car.getMark());
        jsonGenerator.writeStringField("model", car.getModel());

        if (car.getPilotFirstName() == null) {
            jsonGenerator.writeNullField("pilotFirstName");
        } else {
            jsonGenerator.writeStringField("pilotFirstName", car.getPilotFirstName());
        }

        if (car.getPilotLastName() == null) {
            jsonGenerator.writeNullField("pilotLastName");
        } else {
            jsonGenerator.writeStringField("pilotLastName", car.getPilotLastName());
        }

        if (car.getPower() == null) {
            jsonGenerator.writeNullField("power");
        } else {
            jsonGenerator.writeNumberField("power", car.getPower());
        }

        if (car.getTorque() == null) {
            jsonGenerator.writeNullField("torque");
        } else {
            jsonGenerator.writeNumberField("torque", car.getTorque());
        }

        if (car.getSpec() == null) {
            jsonGenerator.writeNullField("spec");
        } else {
            jsonGenerator.writeStringField("spec", car.getSpec());
        }

        if (car.getSponsor() == null) {
            jsonGenerator.writeNullField("sponsorId");
        } else {
            jsonGenerator.writeNumberField("sponsorId", car.getSponsor().getSponsorId());
        }

        jsonGenerator.writeEndObject();

    }

}
