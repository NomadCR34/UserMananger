package ir.amin.app.usermanager.password;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    @Query("SELECT t FROM password t WHERE t.userId = ?1")
    List<Password> findPasswordByUserId(Long userID);

    @Query("select case when(count(t) > 0) then true else false end from password t" +
            " where t.userId = ?1 and t.password = ?2 ")
    Boolean isPasswordExistForUser(Long userID, String password);

    @Query("select case when(count(t) > 0) then true else false end from password t " +
            "where t.userId = ?1 and t.password = ?2 and t.isValid is true")
    Boolean isPasswordExistAndValidForUser(Long userID, String password);
}
