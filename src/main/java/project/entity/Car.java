package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import project.entity.deserializer.CarDeserializer;
import project.entity.serializer.CarSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *  Entity class for "cars" table in dataBase
 */
@Entity
@Table(name = "cars")
@JsonSerialize(using = CarSerializer.class)
@JsonDeserialize(using = CarDeserializer.class)
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

    /**
     * Default constructor for Jackson deserializer
     */
    public Car() {
    }

    /**
     * Constructor for Builder
     * @param carBuilder - exemplar of Builder
     */
    private Car(CarBuilder carBuilder) {
        this.carId = carBuilder.carId;
        this.mark = carBuilder.mark;
        this.model = carBuilder.model;
        this.pilotFirstName = carBuilder.pilotFirstName;
        this.pilotLastName = carBuilder.pilotLastName;
        this.power = carBuilder.power;
        this.torque = carBuilder.torque;
        this.spec = carBuilder.spec;
        this.sponsor = carBuilder.sponsor;
    }

    /**
     * Getter for carId field
     * @return - carId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", unique = true, nullable = false)
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    /**
     * Getter for mark field
     * @return - mark
     */
    @Column(name = "mark", nullable = false, length = 50)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * Getter for model field
     * @return - model
     */
    @Column(name = "model", nullable = false, length = 50)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter for pilot first name
     * @return - pilot first name
     */
    @Column(name = "pilot_first_name", length = 50)
    public String getPilotFirstName() {
        return pilotFirstName;
    }

    public void setPilotFirstName(String pilotFirstName) {
        this.pilotFirstName = pilotFirstName;
    }

    /**
     * Getter for pilot last name
     * @return - pilot last name
     */
    @Column(name = "pilot_last_name", length = 50)
    public String getPilotLastName() {
        return pilotLastName;
    }

    public void setPilotLastName(String pilotLastName) {
        this.pilotLastName = pilotLastName;
    }

    /**
     * Getter for power field
     * @return - power
     */
    @Column(name = "power")
    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    /**
     * Getter for torque field
     * @return - torque
     */
    @Column(name = "torque")
    public Integer getTorque() {
        return torque;
    }

    public void setTorque(Integer torque) {
        this.torque = torque;
    }

    /**
     * Getter for spec
     * @return - spec
     */
    @Column(name = "spec")
    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * Getter for sponsor of car
     * @return - sponsor
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_id")
    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * Getter for set of Races, where car take part in
     * @return - set of races
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(Set<Race> races) {
        this.races = races;
    }

    /**
     * Override dafault toString method
     * @return - string representation of object
     */
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

    /**
     * Inner class for Builder pattern
     */
    public static class CarBuilder {

        private Long carId;
        private final String mark;
        private final String model;
        private String pilotFirstName;
        private String pilotLastName;
        private Integer power;
        private Integer torque;
        private String spec;
        private Sponsor sponsor;

        /**
         * Constructor for builder. Take mandatory params for NOT NULL column in database
         * @param mark - mark of car
         * @param model - model of car
         */
        public CarBuilder(String mark, String model) {
            this.mark = mark;
            this.model = model;
        }

        /**
         * Insert car id
         * @param carId - car ID
         * @return - builder
         */
        public CarBuilder carId(Long carId) {
            this.carId = carId;
            return this;
        }

        /**
         * Insert pilot first name
         * @param pilotFirstName - pilot first name
         * @return - builder
         */
        public CarBuilder pilotFirstName(String pilotFirstName) {
            this.pilotFirstName = pilotFirstName;
            return this;
        }

        /**
         * Insert pilot last name
         * @param pilotLastName - pilot last name
         * @return - builder
         */
        public CarBuilder pilotLastName(String pilotLastName) {
            this.pilotLastName = pilotLastName;
            return this;
        }

        /**
         * Insert power
         * @param power - power
         * @return - builder
         */
        public CarBuilder power(Integer power) {
            this.power = power;
            return this;
        }

        /**
         * Insert torque
         * @param torque - torque
         * @return - builder
         */
        public CarBuilder torque(Integer torque) {
            this.torque = torque;
            return this;
        }

        /**
         * Insert spec
         * @param spec - spec
         * @return - builder
         */
        public CarBuilder spec(String spec) {
            this.spec = spec;
            return this;
        }

        /**
         * Insert Sponsor
         * @param sponsor - sponsor
         * @return - builder
         */
        public CarBuilder sponsor(Sponsor sponsor) {
            this.sponsor = sponsor;
            return this;
        }

        /**
         * Build object "Car'
         * @return - Car
         */
        public Car build() {
            return new Car(this);
        }

    }
}
