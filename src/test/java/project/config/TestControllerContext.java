package project.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.service.TrackService;

@Configuration
public class TestControllerContext {

    @Bean
    public TrackService trackService() {
        return Mockito.mock(TrackService.class);
    }

}
