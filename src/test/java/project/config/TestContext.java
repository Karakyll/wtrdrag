package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import project.entity.Track;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class TestContext {

    @Autowired
    private WebApplicationContext context;

    @Bean
    public List<Track> trackList() {
        Track first = new Track();
        first.setTrackId(1L);
        first.setTrackName("First Track");
        first.setTrackCountry("First Country");

        Track second = new Track();
        second.setTrackId(2L);
        second.setTrackName("Second Track");
        second.setTrackCountry("Second Country");

        return Arrays.asList(first,second);
    }

    @Bean
    public MockMvc mockMvc() {
        return MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Bean
    public MediaType application_JSON_UTF8() {
        return new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8")
        );
    }

}
