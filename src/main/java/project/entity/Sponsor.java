package project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sponsors")
public class Sponsor implements Serializable{

    private Long sponsorId;
    private String sponsorName;
    private String sponsorSlogan;
    private Set<Car> carEntities = new HashSet<>(0);

    public Sponsor(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sponsor_id", unique = true, nullable = false)
    public Long getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Long sponsorId) {
        this.sponsorId = sponsorId;
    }

    @Column(name = "sponsor_name", unique = true, nullable = false, length = 50)
    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    @Column(name = "sponsor_slogan")
    public String getSponsorSlogan() {
        return sponsorSlogan;
    }

    public void setSponsorSlogan(String sponsorSlogan) {
        this.sponsorSlogan = sponsorSlogan;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sponsor")
    public Set<Car> getCarEntities() {
        return carEntities;
    }

    public void setCarEntities(Set<Car> carEntities) {
        this.carEntities = carEntities;
    }

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
}
