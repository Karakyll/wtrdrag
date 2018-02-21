package project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception user not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(Long carId) {
        super("could not find car with id=[" + carId +"].");
    }

}
