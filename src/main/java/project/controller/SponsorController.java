package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.entity.Sponsor;
import project.service.SponsorService;

import java.net.URI;
import java.util.List;

/**
 * Rest controller class for "/sponsors/**" requests
 */
@RestController
@RequestMapping(value = "/sponsors")
@CrossOrigin
public class SponsorController {

    @Autowired
    SponsorService sponsorService;

    /**
     * Map all "/sponsors" GET requests
     * @return - JSON with all sponsors
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Sponsor> getAllSponsors() {
        return this.sponsorService.getAllSponsors();
    }

    /**
     * Map all "/sponsors/{id}" GET requests
     * @param sponsorId - sponsor identity
     * @return - JSON sponsor with {id}
     */
    @RequestMapping(value = "/{sponsorId}", method = RequestMethod.GET)
    Sponsor getSponsor(@PathVariable Long sponsorId) {
        return sponsorService.findSponsorById(sponsorId);
    }

    /**
     * Map all "/sponsors" POST requests
     * @param inputSponsor - take JSON sponsor in body
     * @return - status "created" with link for created resource
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addSponsor(@RequestBody Sponsor inputSponsor) {
        if (sponsorService.findBySponsorName(inputSponsor.getSponsorName()).isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            Sponsor result = sponsorService.save(inputSponsor);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{sponsorId}")
                    .buildAndExpand(result.getSponsorId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }


}
