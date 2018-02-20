package project.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SponsorNotFoundExeption extends RuntimeException{
    
    public SponsorNotFoundExeption(Long sponsorId) {
        super("could not find sponsor with id=[" + sponsorId +"].");
    }

}
