package project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception track not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrackNotFoundException extends  RuntimeException{

    public TrackNotFoundException(Long trackId) {
        super("could not find track with id=[" + trackId +"].");
    }

}
