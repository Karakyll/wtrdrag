package project.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import project.entity.Track;
import project.repository.TrackRepository;
import project.service.TrackService;
import project.service.implementation.TrackServiceImpl;

public class TestServiceContext {

    @Bean
    public TrackService trackServiceImpl() {
        return new TrackServiceImpl();
    }

    @Bean
    public TrackRepository trackRepository() {
        return Mockito.mock(TrackRepository.class);
    }

}
