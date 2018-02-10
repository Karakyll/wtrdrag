package main.java.project.sponsor;

import main.java.project.car.Car;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sponsors")
public class Sponsor implements Serializable{

    private Integer sponsorId;
    private String sponsorName;
    private String sponsorSlogan;
    private Set<Car> cars = new HashSet<>(0);

    public Sponsor(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sponsor_id", unique = true, nullable = false)
    public Integer getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Integer sponsorId) {
        this.sponsorId = sponsorId;
    }

    @Column(name = "sponsor_name", unique = true, nullable = false, length = 50)
    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    @Column(name = "slogan")
    public String getSponsorSlogan() {
        return sponsorSlogan;
    }

    public void setSponsorSlogan(String sponsorSlogan) {
        this.sponsorSlogan = sponsorSlogan;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sponsor")
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
