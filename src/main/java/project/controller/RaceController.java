package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.entity.Race;
import project.service.RaceService;

import java.util.List;

/**
 * Rest controller class for "/races/**" requests
 */
@RestController
@RequestMapping(value = "/races")
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
        return raceService.findRaceById(raceId);
    }



}
