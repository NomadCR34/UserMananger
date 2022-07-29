package ir.amin.app.usermanager.password;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "password")
public class PasswordConfig {
    private short minLength;
    private short maxLength;
    private String salt;

    public short getMinLength() {
        return minLength;
    }

    public void setMinLength(short minLength) {
        this.minLength = minLength;
    }

    public short getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(short maxLength) {
        this.maxLength = maxLength;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
