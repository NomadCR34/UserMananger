package ir.amin.app.usermanager.util.invalidator;

import ir.amin.app.usermanager.util.invalidator.DataValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator extends DataValidator {

    Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
    @Override
    public Pattern getPattern() {
        return VALID_EMAIL_ADDRESS_REGEX;
    }
}
