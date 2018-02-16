package project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car implements Serializable{

    private Long carId;
    private String mark;
    private String model;
    private String pilotFirstName;
    private String pilotLastName;
    private Integer power;
    private Integer torque;
    private String spec;
    private Sponsor sponsor;
    private Set<Race> races = new HashSet<>(0);

    public Car() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", unique = true, nullable = false)
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Column(name = "mark", nullable = false, length = 50)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Column(name = "model", nullable = false, length = 50)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "pilot_first_name", length = 50)
    public String getPilotFirstName() {
        return pilotFirstName;
    }

    public void setPilotFirstName(String pilotFirstName) {
        this.pilotFirstName = pilotFirstName;
    }

    @Column(name = "pilot_last_name", length = 50)
    public String getPilotLastName() {
        return pilotLastName;
    }

    public void setPilotLastName(String pilotLastName) {
        this.pilotLastName = pilotLastName;
    }

    @Column(name = "power")
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Column(name = "torque")
    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    @Column(name = "spec")
    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_id", nullable = false)
    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(Set<Race> races) {
        this.races = races;
    }

    @Override
    public String toString(){
        return String.format("Car [id:  %-5d" +
                "Mark:  %-15s" +
                "Model: %-15s" +
                "Pilot first name:  %-20s" +
                "Pilot last name:  %-20s" +
                "Power:  %-7d" +
                "Torque:  %-7d" +
                "Sponsor:  %-5d" +
                "Spec:  %-50s" +
                "]",
                carId,
                mark,
                model,
                pilotFirstName,
                pilotLastName,
                power,
                torque,
                sponsor.getSponsorId(),
                spec);
    }
}
