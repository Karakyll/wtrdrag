package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Sponsor;
import project.repository.SponsorRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service("sponsorService")
public class SponsorServiceImpl implements project.service.SponsorService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    SponsorRepository sponsorRepository;

    @Override
    public Sponsor save(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    @Override
    public void delete(Sponsor sponsor) {
        sponsorRepository.delete(sponsor);
    }

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    public List<Sponsor> getAllSponsorsUsingNativeQuery() {
        return entityManager.createNativeQuery("SELECT * FROM sponsors", Sponsor.class).getResultList();
    }

}
