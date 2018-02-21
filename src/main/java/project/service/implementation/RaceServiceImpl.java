package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Race;
import project.exceptions.RaceNotFoundException;
import project.repository.RaceRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Class for race service operations
 */
@Service("raceService")
public class RaceServiceImpl implements project.service.RaceService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    RaceRepository raceRepository;

    /**
     * Find all races in dataBase
     * @return - list of races
     */
    @Override
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    /**
     * Find race with ID in dataBase
     * @param raceId - race identity
     * @return - race
     */
    @Override
    public Race findRaceById(Long raceId) {
        return raceRepository.findById(raceId).orElseThrow(() -> new RaceNotFoundException(raceId));
    }
}
