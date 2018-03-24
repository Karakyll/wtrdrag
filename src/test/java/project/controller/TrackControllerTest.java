package project.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import project.config.AppConfig;
import project.config.TestContext;
import project.config.TestControllerContext;
import project.entity.Track;
import project.exceptions.TrackNotFoundException;
import project.service.TrackService;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, TestControllerContext.class, AppConfig.class})
@WebAppConfiguration
public class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrackService trackServiceMock;

    @Autowired
    private MediaType application_JSON_UTF8;

    @Autowired
    private List<Track> tracks;

    private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    @Test
    public void getAllTracks_TracksFound_ShouldReturnFoundTracksEntries() throws Exception {

        when(trackServiceMock.getAllTracks()).thenReturn(tracks);

        mockMvc.perform(get("/tracks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].trackId", is(1)))
                .andExpect(jsonPath("$[0].trackName", is("First Track")))
                .andExpect(jsonPath("$[0].trackCountry", is("First Country")))
                .andExpect(jsonPath("$[1].trackId", is(2)))
                .andExpect(jsonPath("$[1].trackName", is("Second Track")))
                .andExpect(jsonPath("$[1].trackCountry", is("Second Country")));

        verify(trackServiceMock, times(1)).getAllTracks();
        verifyNoMoreInteractions(trackServiceMock);
    }

    @Test
    public void getTrackById_TrackNotFound_ShouldReturnStatusCode404() throws Exception {

        when(trackServiceMock.findTrackById(1L)).thenThrow(new TrackNotFoundException(1L));

        mockMvc.perform(get("/tracks/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(trackServiceMock, times(1)).findTrackById(1L);
        verifyNoMoreInteractions(trackServiceMock);
    }

    @Test
    public void addNewTrack_ShouldAddNewTrackAndReturnAddedTrackAndResourceURI() throws Exception {

        Track postedTrack = new Track();
        postedTrack.setTrackName("Track");
        postedTrack.setTrackCountry("Country");

        Track addedTrack = new Track();
        addedTrack.setTrackId(1L);
        addedTrack.setTrackName("Track");
        addedTrack.setTrackCountry("Country");

        when(trackServiceMock.save((Track) any())).thenReturn(addedTrack);

        mockMvc.perform(post("/tracks")
                .contentType(application_JSON_UTF8)
                .content(convertObjectToJsonBytes(postedTrack))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$.trackId", is(1)))
                .andExpect(jsonPath("$.trackName", is("Track")))
                .andExpect(jsonPath("$.trackCountry", is("Country")));

        ArgumentCaptor<Track> trackCaptor = ArgumentCaptor.forClass(Track.class);
        verify(trackServiceMock, times(1)).save(trackCaptor.capture());
        verify(trackServiceMock, times(1)).findByTrackName(postedTrack.getTrackName());
        verifyNoMoreInteractions(trackServiceMock);

        Track track = trackCaptor.getValue();
        assertNull(track.getTrackId());
        assertThat(track.getTrackName(), is("Track"));
        assertThat(track.getTrackCountry(), is("Country"));


    }
}