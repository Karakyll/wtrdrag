package project.service;

import project.entity.Race;

import java.util.List;
import java.util.Optional;

public interface RaceService {

    Race save(final Race race);

    List<Race> getAllRaces();

    Optional<Race> findRaceById(Long raceId);

    void deleteRaceById(Long raceId);

}
