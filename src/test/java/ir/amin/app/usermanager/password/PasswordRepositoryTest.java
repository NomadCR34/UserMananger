package ir.amin.app.usermanager.password;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PasswordRepositoryTest {

    @Autowired
    private PasswordRepository passwordRepository;

    @AfterEach
    void tearDown() {
        passwordRepository.deleteAll();
    }

    @Test
    void findPasswordByUserId() {
        Password password = new Password(
                "123",
                System.currentTimeMillis(),
                true,
                1L
        );
        passwordRepository.save(password);
        List<Password> passwords = passwordRepository.findPasswordByUserId(1L);
        assertThat(password).isEqualTo(passwords.get(0));
    }

    @Test
    void isPasswordExistForUser() {
        Password password = new Password(
                "123",
                System.currentTimeMillis(),
                true,
                1L
        );
        passwordRepository.save(password);
        assertThat(passwordRepository.isPasswordExistForUser(1L, "123")).isTrue();
    }

    @Test
    void isPasswordNotExistForUser() {
        Password password = new Password(
                "123",
                System.currentTimeMillis(),
                true,
                1L
        );
        passwordRepository.save(password);
        assertThat(passwordRepository.isPasswordExistForUser(1L, "1234")).isFalse();
    }

    @Test
    void isPasswordExistAndValidForUser() {
        Password password = new Password(
                "123",
                System.currentTimeMillis(),
                true,
                1L
        );
        passwordRepository.save(password);
        assertThat(passwordRepository.isPasswordExistAndValidForUser(1L, "123")).isTrue();
    }


    @Test
    void isPasswordExistAndNotValidForUser() {
        Password password = new Password(
                "123",
                System.currentTimeMillis(),
                false,
                1L
        );
        passwordRepository.save(password);
        assertThat(passwordRepository.isPasswordExistAndValidForUser(1L, "123")).isFalse();
    }

}