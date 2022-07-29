package ir.amin.app.usermanager.password;


import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Table
@Entity(name = "password")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PASSWORD_DATA")
    @SequenceGenerator(name = "PASSWORD_DATA", sequenceName = "PASSWORD_DATA", allocationSize = 1)
    private Long id;

    @Column(name = "password", length = 64, nullable = false)
    @NonNull
    private String password;

    @NonNull
    @Column(name = "createDate", nullable = false)
    private Long createDate;

    @NonNull
    @Column(name = "isValid", nullable = false)
    private Boolean isValid;

    @NonNull
    @Column(name = "userId", nullable = false)
    private Long userId;

    public Password() {
    }

    public Password(String password, Long userId) {
        this.password = password;
        this.createDate = createDate;
        this.isValid = isValid;
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Boolean isValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", isValid=" + isValid +
                ", userId=" + userId +
                '}';
    }
}

