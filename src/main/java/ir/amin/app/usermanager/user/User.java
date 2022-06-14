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

    private Short countryID;

    private Short provinceID;

    private Short cityID;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 100, nullable = false)
    private String address;

    public User() {
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

    public Short getCountryID() {
        return countryID;
    }

    public void setCountryID(Short countryID) {
        this.countryID = countryID;
    }

    public Short getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Short provinceID) {
        this.provinceID = provinceID;
    }

    public Short getCityID() {
        return cityID;
    }

    public void setCityID(Short cityID) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", registerDate=" + registerDate +
                ", modifiedDate=" + modifiedDate +
                ", lastLoginDate=" + lastLoginDate +
                ", genderID=" + genderID +
                ", nationalID='" + nationalID + '\'' +
                ", countryID=" + countryID +
                ", provinceID=" + provinceID +
                ", cityID=" + cityID +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
