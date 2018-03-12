package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Race;
import project.exceptions.RaceNotFoundException;
import project.repository.RaceRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Class for race service operations
 */
@Service("raceService")
@Transactional
public class RaceServiceImpl implements project.service.RaceService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    RaceRepository raceRepository;

    @Override
    public Race save(Race race) {
        return raceRepository.save(race);
    }

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
    public Optional<Race> findRaceById(Long raceId) {
        return raceRepository.findById(raceId);
    }

    @Override
    public void deleteRaceById(Long raceId){
        raceRepository.deleteById(raceId);
    }
}
