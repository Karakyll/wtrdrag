package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.entity.Track;
import project.service.TrackService;

import java.net.URI;
import java.util.List;

/**
 * Rest controller class for "/tracks/**" requests
 */
@RestController
@RequestMapping(value = "/tracks")
@CrossOrigin
public class TrackController {

    @Autowired
    TrackService trackService;

    /**
     * Map all "/tracks" GET requests
     * @return - JSON with all tracks
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Track> getAllTracks() {
        return this.trackService.getAllTracks();
    }

    /**
     * Map all "/tracks/{id}" GET requests
     * @param trackId - track identity
     * @return JSON track with {id}
     */
    @RequestMapping(value = "/{trackId}", method = RequestMethod.GET)
    Track getTrack(@PathVariable Long trackId) {
        return trackService.findTrackById(trackId);
    }

    /**
     * Map all "/tracks" POST requests
     * @param inputTrack - take JSON track in body
     * @return - status "created" with link to created resource
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addTrack(@RequestBody Track inputTrack) {
        if (trackService.findByTrackName(inputTrack.getTrackName()).isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            Track result = trackService.save(inputTrack);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{trackId}")
                    .buildAndExpand(result.getTrackId()).toUri();
            return ResponseEntity.created(location).build();
        }

    }



}
