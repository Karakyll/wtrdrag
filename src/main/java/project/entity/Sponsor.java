package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *  Entity class for "sponsors" table in dataBase
 */
@Entity
@Table(name = "sponsors")
public class Sponsor implements Serializable{

    private Long sponsorId;
    private String sponsorName;
    private String sponsorSlogan;
    private Set<Car> carEntities = new HashSet<>(0);

    /**
     * Default constructor for Jackson deserializer
     */
    public Sponsor(){
    }

    /**
     * Constructor for builder
     * @param sponsorBuilder - sponsor builder
     */
    private Sponsor(SponsorBuilder sponsorBuilder) {
        this.sponsorName = sponsorBuilder.sponsorName;
        this.sponsorSlogan = sponsorBuilder.sponsorSlogan;
    }

    /**
     * Getter for sponsor id
     * @return - sponsor id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sponsor_id", unique = true, nullable = false)
    public Long getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Long sponsorId) {
        this.sponsorId = sponsorId;
    }

    /**
     * Getter for sponsor name
     * @return - sponsor name
     */
    @Column(name = "sponsor_name", unique = true, nullable = false, length = 50)
    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    /**
     * Getter for sponsor slogan
     * @return - sponsor slogan
     */
    @Column(name = "sponsor_slogan")
    public String getSponsorSlogan() {
        return sponsorSlogan;
    }

    public void setSponsorSlogan(String sponsorSlogan) {
        this.sponsorSlogan = sponsorSlogan;
    }

    /**
     * Getter for set of cars
     * @return - set of cars
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sponsor")
    public Set<Car> getCarEntities() {
        return carEntities;
    }

    public void setCarEntities(Set<Car> carEntities) {
        this.carEntities = carEntities;
    }

    /**
     * Override default method toString
     * @return - string representation of object
     */
    @Override
    public String toString(){
        return String.format("Sponsor [id:  %-5d" +
                "Name:  %-30s" +
                "Slogan:  %-50s" +
                "]",
                sponsorId,
                sponsorName,
                sponsorSlogan);
    }

    /**
     * Inner class for Builder pattern
     */
    public static class SponsorBuilder {
        private String sponsorName;
        private String sponsorSlogan;

        /**
         * Constructor for builder. Take mandatory params for NOT NULL column in database
         * @param sponsorName - sponsor name
         */
        public SponsorBuilder(String sponsorName) {
            this.sponsorName = sponsorName;
        }

        /**
         * Insert sponsor slogan
         * @param sponsorSlogan - sponsor slogan
         * @return - builder
         */
        public SponsorBuilder sponsorSlogan(String sponsorSlogan) {
            this.sponsorSlogan = sponsorSlogan;
            return this;
        }

        /**
         * Build method
         * @return - Sponsor
         */
        public Sponsor build() {
            return new Sponsor(this);
        }

    }
}
