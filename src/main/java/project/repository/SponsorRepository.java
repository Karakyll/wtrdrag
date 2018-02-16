package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Sponsor;

import java.util.List;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    List<Sponsor> findAll();

}
