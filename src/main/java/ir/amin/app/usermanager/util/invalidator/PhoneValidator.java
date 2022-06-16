package ir.amin.app.usermanager.util.invalidator;

import ir.amin.app.usermanager.util.invalidator.DataValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneValidator extends DataValidator {

    private final static Short PHONE_LENGTH = 10;

    Pattern VALID_PHONE_REGEX =
            Pattern.compile("^[0-9]{" + PHONE_LENGTH + "}+$");

    @Override
    public Pattern getPattern() {
        return VALID_PHONE_REGEX;
    }
}
