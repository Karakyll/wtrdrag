package project.service;

import project.entity.Race;

import java.util.List;

public interface RaceService {

    List<Race> getAllRaces();

   Race findRaceById(Long raceId);

}
