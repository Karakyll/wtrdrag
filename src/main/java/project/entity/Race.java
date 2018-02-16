package project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "races")
public class Race implements Serializable{

    private Long raceId;
    private Car car;
    private Track track;
    private Date dateTime;
    private Double reactionTime;
    private Double elapsedTime;
    private Double finishSpeed;

    public Race() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "race_id", unique = true, nullable = false)
    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
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
    public Double getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(Double reactionTime) {
        this.reactionTime = reactionTime;
    }

    @Column(name = "elapsed_time", nullable = false)
    public Double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    @Column(name = "finish_speed", nullable = false)
    public Double getFinishSpeed() {
        return finishSpeed;
    }

    public void setFinishSpeed(Double finishSpeed) {
        this.finishSpeed = finishSpeed;
    }

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
