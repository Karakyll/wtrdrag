package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Race;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {

    List<Race> findAll();

}
