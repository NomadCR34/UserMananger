package ir.amin.app.usermanager.gender;

import javax.persistence.*;

@Entity(name = "gender")
@Table(name = "tbl_gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENDER_DATA")
    @SequenceGenerator(sequenceName = "gender_seq", allocationSize = 1, name = "GENDER_DATA")
    private Short id;
    @Column(length = 15, nullable = false)
    private String name;

    public Gender() {
    }

    public Gender(String name) {
        this.name = name;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
