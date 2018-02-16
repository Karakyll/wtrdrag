package project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tracks")
public class Track implements Serializable{

    private Long trackId;
    private String trackName;
    private String trackCountry;
    private Set<Race> raceEntities = new HashSet<>(0);

    public Track(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id", unique = true, nullable = false)
    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    @Column(name = "track_name", unique = true, nullable = false, length = 50)
    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    @Column(name = "track_country", length = 50)
    public String getTrackCountry() {
        return trackCountry;
    }

    public void setTrackCountry(String trackCountry) {
        this.trackCountry = trackCountry;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "track")
    public Set<Race> getRaceEntities() {
        return raceEntities;
    }

    public void setRaceEntities(Set<Race> raceEntities) {
        this.raceEntities = raceEntities;
    }

    @Override
    public String toString(){
        return String.format("Track [id:  %-5d" +
                "Name:  %-25s" +
                "Country:  %-20s" +
                "]",
                trackId,
                trackName,
                trackCountry);
    }
}
