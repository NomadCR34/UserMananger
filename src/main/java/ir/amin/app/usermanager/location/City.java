package ir.amin.app.usermanager.location;

import javax.persistence.*;

@Table
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITY_DATA")
    @SequenceGenerator(sequenceName = "city_seq", allocationSize = 1, name = "CITY_DATA")
    private Long id;

    @Column(length = 40,nullable = false)
    private String name;

    private Long provinceID;

    private Double lat;

    private Double lng;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public City() {
    }

    public City(String name, Long provinceID, Double lat, Double lng) {
        this.name = name;
        this.provinceID = provinceID;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Long provinceID) {
        this.provinceID = provinceID;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provinceID=" + provinceID +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
