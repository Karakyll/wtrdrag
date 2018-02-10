package main.java.project.track;

import main.java.project.race.Race;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tracks")
public class Track implements Serializable{

    private Integer trackId;
    private String trackName;
    private String country;
    private Set<Race> races = new HashSet<>(0);

    public Track(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id", unique = true, nullable = false)
    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    @Column(name = "track_name", unique = true, nullable = false, length = 50)
    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    @Column(name = "country", length = 50)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "track")
    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(Set<Race> races) {
        this.races = races;
    }
}
