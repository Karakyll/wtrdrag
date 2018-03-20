package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *  Entity class for "tracks" table in dataBase
 */
@Entity
@Table(name = "tracks")
public class Track implements Serializable{

    private Long trackId;
    private String trackName;
    private String trackCountry;
    private Set<Race> raceEntities = new HashSet<>(0);

    /**
     * Default constructor for Jackson deserializer
     */
    public Track(){
    }

    /**
     * Getter for track id
     * @return - track id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id", unique = true, nullable = false)
    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    /**
     * Getter for track name
     * @return - track name
     */
    @Column(name = "track_name", unique = true, nullable = false, length = 50)
    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     * Getter for track country
     * @return - track country
     */
    @Column(name = "track_country", length = 50)
    public String getTrackCountry() {
        return trackCountry;
    }

    public void setTrackCountry(String trackCountry) {
        this.trackCountry = trackCountry;
    }

    /**
     * Getter for set of races, which take place
     * @return - set of races
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "track")
    public Set<Race> getRaceEntities() {
        return raceEntities;
    }

    public void setRaceEntities(Set<Race> raceEntities) {
        this.raceEntities = raceEntities;
    }

    /**
     * Override default method toString
     * @return - string representation of object
     */
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
