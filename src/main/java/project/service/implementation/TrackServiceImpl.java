package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Track;
import project.repository.TrackRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service("trackService")
public class TrackServiceImpl implements project.service.TrackService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TrackRepository trackRepository;

    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }
}
