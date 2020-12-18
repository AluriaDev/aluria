package io.github.aluria.kingdoms.validators.kingdom;

import io.github.aluria.kingdoms.validators.Validator;

import java.util.regex.Pattern;

public class KingdomNameValidator implements Validator<String> {

    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9 ]{4,12}");

    @Override
    public boolean validate(String value) {
        return NAME_PATTERN.matcher(value).matches();
    }
}
