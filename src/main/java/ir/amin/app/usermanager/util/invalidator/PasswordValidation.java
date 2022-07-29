package ir.amin.app.usermanager.util.invalidator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordValidation extends DataValidator{

    private final Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=])(?=\\S+$)$");

    @Override
    public Pattern getPattern() {
        return pattern;
    }
}
