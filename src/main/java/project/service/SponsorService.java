package project.service;

import project.entity.Sponsor;

import java.util.List;
import java.util.Optional;

public interface SponsorService {

    Sponsor save(final Sponsor sponsor);

    void delete(final Sponsor sponsor);

    List<Sponsor> getAllSponsors();

    List<Sponsor> getAllSponsorsUsingNativeQuery();

    Sponsor findSponsorById(Long sponsorId);

    Optional<Sponsor> findBySponsorName(String sponsorName);

}
