package ir.amin.app.usermanager.user;

import ir.amin.app.usermanager.gender.Gender;

import javax.persistence.*;

@Entity(name = "user")
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30, nullable = false)
    private String firstName;

    @Column(length = 30)
    private String middleName;

    @Column(length = 50, nullable = false)
    private String lastName;

    private Long birthDate;

    private Long registerDate;

    private Long modifiedDate;

    private Long lastLoginDate;

    private Short genderID;

    @Column(nullable = false)
    private String nationalID;

    private Integer countryID;

    private Long provinceID;

    private Long cityID;

    private Long birthPlaceCity;

    private Short degree;

    private Short language;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 100, nullable = false)
    private String address;

    public User() {
    }

    public User(
            String firstName,
            String middleName,
            String lastName,
            Long birthDate,
            Long registerDate,
            Long modifiedDate,
            Long lastLoginDate,
            Short genderID,
            String nationalID,
            Integer countryID,
            Long provinceID,
            Long cityID,
            Long birthPlaceCity,
            Short degree,
            Short language,
            String email,
            String phone,
            String address
    ) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.registerDate = registerDate;
        this.modifiedDate = modifiedDate;
        this.lastLoginDate = lastLoginDate;
        this.genderID = genderID;
        this.nationalID = nationalID;
        this.countryID = countryID;
        this.provinceID = provinceID;
        this.cityID = cityID;
        this.birthPlaceCity = birthPlaceCity;
        this.degree = degree;
        this.language = language;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public Long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Long registerDate) {
        this.registerDate = registerDate;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Short getGenderID() {
        return genderID;
    }

    public void setGenderID(Short genderID) {
        this.genderID = genderID;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public Long getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Long provinceID) {
        this.provinceID = provinceID;
    }

    public Long getCityID() {
        return cityID;
    }

    public void setCityID(Long cityID) {
        this.cityID = cityID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBirthPlaceCity() {
        return birthPlaceCity;
    }

    public void setBirthPlaceCity(Long birthPlaceCity) {
        this.birthPlaceCity = birthPlaceCity;
    }

    public Short getDegree() {
        return degree;
    }

    public void setDegree(Short degree) {
        this.degree = degree;
    }

    public Short getLanguage() {
        return language;
    }

    public void setLanguage(Short language) {
        this.language = language;
    }
}
