package project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception sponsor not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SponsorNotFoundException extends RuntimeException{

    public SponsorNotFoundException(Long sponsorId) {
        super("could not find sponsor with id=[" + sponsorId +"].");
    }

}
