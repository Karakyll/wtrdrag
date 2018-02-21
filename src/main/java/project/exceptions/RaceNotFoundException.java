package project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception race not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RaceNotFoundException extends RuntimeException{

    public RaceNotFoundException(Long raceId) {
        super("could not find race with id=[" + raceId +"].");
    }

}
