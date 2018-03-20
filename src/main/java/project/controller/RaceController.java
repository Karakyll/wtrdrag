package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.entity.Race;
import project.exceptions.RaceNotFoundException;
import project.service.RaceService;

import java.net.URI;
import java.util.List;

/**
 * Rest controller class for "/races/**" requests
 */
@RestController
@RequestMapping(value = "/races")
@CrossOrigin
public class RaceController {

    @Autowired
    RaceService raceService;

    /**
     * Map all "/races" GET requests
     * @return - JSON wirth all races
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Race> getAllRaces() {
        return this.raceService.getAllRaces();
    }

    /**
     * Map all "/races/{id}" GET requests
     * @param raceId - race identity
     * @return - JSON race with {id}
     */
    @RequestMapping(value = "/{raceId}", method = RequestMethod.GET)
    Race getRace(@PathVariable Long raceId) {
        return raceService.findRaceById(raceId).orElseThrow(() -> new RaceNotFoundException(raceId));
    }

    /**
     * Map all "/races" POST requests
     * @param inputRace - race object
     * @return - response with object in body
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addRace(@RequestBody Race inputRace) {
            Race result = raceService.save(inputRace);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{raceId}")
                    .buildAndExpand(result.getRaceId()).toUri();
            return ResponseEntity.created(location).body(inputRace);
    }

    /**
     * Map all "/races/{id}" DELETE requests
     * @param raceId - race identity
     * @return - response status
     */
    @RequestMapping(value = "/{raceId}", method = RequestMethod.DELETE)
    ResponseEntity deleteRace(@PathVariable Long raceId){
        if (raceService.findRaceById(raceId).isPresent()) {
            raceService.deleteRaceById(raceId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
