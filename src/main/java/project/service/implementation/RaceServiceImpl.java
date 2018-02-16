package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Race;
import project.repository.RaceRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service("raceService")
public class RaceServiceImpl implements project.service.RaceService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    RaceRepository raceRepository;

    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }
}
