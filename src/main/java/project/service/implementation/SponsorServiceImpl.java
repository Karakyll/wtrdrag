package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Sponsor;
import project.exceptions.SponsorNotFoundException;
import project.repository.SponsorRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Class for sponsor service operations
 */
@Service("sponsorService")
public class SponsorServiceImpl implements project.service.SponsorService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    SponsorRepository sponsorRepository;

    /**
     * Save sponsor to database
     * @param sponsor - sponsor exeplar
     * @return - saved sponsor
     */
    @Override
    public Sponsor save(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    /**
     * Delete sponsor from DataBase
     * @param sponsor - sponsor exemplar
     */
    @Override
    public void delete(Sponsor sponsor) {
        sponsorRepository.delete(sponsor);
    }

    /**
     * Find all sponsors from Database
     * @return - list of sponsors
     */
    @Override
    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    /**
     * Find all sponsors from database (use native sql query)
     * @return - list of sponsors
     */
    @Override
    public List<Sponsor> getAllSponsorsUsingNativeQuery() {
        return entityManager.createNativeQuery("SELECT * FROM sponsors", Sponsor.class).getResultList();
    }

    /**
     * Find sponsor with ID in database
     * @param sponsorId - sponsor identity
     * @return - sponsor
     */
    @Override
    public Sponsor findSponsorById(Long sponsorId) {
        return sponsorRepository.findById(sponsorId).orElseThrow(() -> new SponsorNotFoundException(sponsorId));
    }

    /**
     * Find sponsor with NAME in database
     * @param sponsorName - sponsor name
     * @return - optional sponsor
     */
    @Override
    public Optional<Sponsor> findBySponsorName(String sponsorName) {
        return sponsorRepository.findBySponsorName(sponsorName);
    }
}
