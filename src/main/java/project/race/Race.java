package main.java.project.race;

import main.java.project.track.Track;
import main.java.project.car.Car;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "races")
public class Race implements Serializable{

    private Integer raceId;
    private Car car;
    private Track track;
    private Date dateTime;
    private double reactionTime;
    private double elapsedTime;
    private double finishSpeed;

    public Race() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "race_id", unique = true, nullable = false)
    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id", nullable = false)
    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time", nullable = false)
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


    @Column(name = "reaction_time", nullable = false)
    public double getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(double reactionTime) {
        this.reactionTime = reactionTime;
    }

    @Column(name = "elapsed_time", nullable = false)
    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    @Column(name = "finish_speed", nullable = false)
    public double getFinishSpeed() {
        return finishSpeed;
    }

    public void setFinishSpeed(double finishSpeed) {
        this.finishSpeed = finishSpeed;
    }
}
