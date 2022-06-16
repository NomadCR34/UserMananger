package ir.amin.app.usermanager.util.invalidator;

import java.util.regex.Pattern;

abstract public class DataValidator {

    public abstract Pattern getPattern();

    public boolean isValid(String value){
        if (value == null) {
            return false;
        }
        return getPattern().matcher(value).find();
    }

}
