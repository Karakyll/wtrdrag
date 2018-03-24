package project.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import project.config.TestContext;
import project.config.TestServiceContext;
import project.entity.Track;
import project.repository.TrackRepository;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, TestServiceContext.class})
@WebAppConfiguration
public class TrackServiceTest {

    @Autowired
    private TrackRepository trackRepositoryMock;

    @Autowired
    private List<Track> tracks;

    @Autowired
    private TrackService trackService;

    @Test
    public void save() {
    }

    @Test
    public void getAllTracks() {

        when(trackRepositoryMock.findAll()).thenReturn(tracks);

        assertEquals(trackService.getAllTracks(), tracks);

    }

    @Test
    public void findTrackById() {
    }

    @Test
    public void findByTrackName() {
    }
}