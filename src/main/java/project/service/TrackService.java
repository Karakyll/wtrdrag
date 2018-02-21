package project.service;

import project.entity.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    Track save(final Track track);

    List<Track> getAllTracks();

    Track findTrackById(Long trackId);

    Optional<Track> findByTrackName(String trackName);

}
