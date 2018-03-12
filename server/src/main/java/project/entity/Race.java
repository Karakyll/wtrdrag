package project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import project.entity.deserializer.RaceDeserializer;
import project.entity.serializer.RaceSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Entity class for "races" table in dataBase
 */
@Entity
@Table(name = "races")
@JsonSerialize(using = RaceSerializer.class)
@JsonDeserialize(using = RaceDeserializer.class)
public class Race implements Serializable{

    private Long raceId;
    private Car car;
    private Track track;
    private Date dateTime;
    private Double reactionTime;
    private Double elapsedTime;
    private Double finishSpeed;

    /**
     * Default constructor for Jackson deserializer
     */
    public Race() {
    }

    /**
     * Getter for raceId field
     * @return - raceId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "race_id", unique = true, nullable = false)
    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    /**
     * Getter for Car entity, what take part in this race
     * @return - car
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Getter for Track entity, where race was
     * @return - track
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id", nullable = false)
    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    /**
     * Getter for date and time, when race was
     * @return - dateTime
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time", nullable = false)
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Getter for reaction time
     * @return - reaction time
     */
    @Column(name = "reaction_time", nullable = false)
    public Double getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(Double reactionTime) {
        this.reactionTime = reactionTime;
    }

    /**
     * Getter for elapsed time
     * @return - elapsed time
     */
    @Column(name = "elapsed_time", nullable = false)
    public Double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Getter for finish speed
     * @return - finish speed
     */
    @Column(name = "finish_speed", nullable = false)
    public Double getFinishSpeed() {
        return finishSpeed;
    }

    public void setFinishSpeed(Double finishSpeed) {
        this.finishSpeed = finishSpeed;
    }

    /**
     * Override dafault method toString
     * @return - string representation of object
     */
    @Override
    public String toString(){
        return String.format("Race [id:  %-5d" +
                "CarId:  %-5d" +
                "Track:  %-5d" +
                "Date:  %-20s" +
                "Reaction time:  %-10s" +
                "Elapsed time:  %-10s" +
                "Finish speed:  %-10s" +
                "]",
                raceId,
                car.getCarId(),
                track.getTrackId(),
                new SimpleDateFormat("dd.MM.yyyy hh:mm").format(dateTime),
                reactionTime,
                elapsedTime,
                finishSpeed );
    }
}
