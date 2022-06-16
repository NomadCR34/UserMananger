package ir.amin.app.usermanager.location;

import javax.persistence.*;

@Table
@Entity
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVINCE_DATA")
    @SequenceGenerator(sequenceName = "province_seq", allocationSize = 1, name = "PROVINCE_DATA")
    private Long id;

    @Column(length = 40,nullable = false)
    private String name;

    private Integer countryID;

    public Province() {
    }

    public Province(String name, Integer countryID) {
        this.name = name;
        this.countryID = countryID;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryID=" + countryID +
                '}';
    }
}
