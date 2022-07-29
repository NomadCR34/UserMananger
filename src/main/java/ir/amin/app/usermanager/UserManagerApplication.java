package ir.amin.app.usermanager;

import ir.amin.app.usermanager.password.PasswordConfig;
import ir.amin.app.usermanager.password.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties({PasswordConfig.class})
public class UserManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class, args);
    }

}
