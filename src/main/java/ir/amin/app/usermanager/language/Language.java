package ir.amin.app.usermanager.language;

import javax.persistence.*;

@Table
@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANG_DATA")
    @SequenceGenerator(sequenceName = "lang_seq", allocationSize = 1, name = "LANG_DATA")
    private Integer id;
    private String name;

    public Language() {
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
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
