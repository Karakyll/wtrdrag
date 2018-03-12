package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Track;
import project.exceptions.TrackNotFoundException;
import project.repository.TrackRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Class for track service operations
 */
@Service("trackService")
@Transactional
public class TrackServiceImpl implements project.service.TrackService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TrackRepository trackRepository;

    /**
     * Save track to database
     * @param track - track exemplar
     * @return - track
     */
    @Override
    public Track save(Track track) {
        return trackRepository.save(track);
    }

    /**
     * Find all track in database
     * @return - list of tracks
     */
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    /**
     * Find track with ID in database
     * @param trackId - track id
     * @return - track
     */
    @Override
    public Track findTrackById(Long trackId) {
        return trackRepository.findById(trackId).orElseThrow(() -> new TrackNotFoundException(trackId));
    }

    /**
     * Find track with Name in database
     * @param trackName - track name
     * @return - optional track
     */
    @Override
    public Optional<Track> findByTrackName(String trackName) {
        return trackRepository.findByTrackName(trackName);
    }

}
