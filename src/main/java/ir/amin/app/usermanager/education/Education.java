package ir.amin.app.usermanager.education;

import javax.persistence.*;

@Entity
@Table
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EDU_DATA")
    @SequenceGenerator(sequenceName = "edu_seq", allocationSize = 1, name = "EDU_DATA")
    private Integer id;
    private String name;

    public Education() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
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
        return "Education{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
