package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Track;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Long> {

    List<Track> findAll();

}
