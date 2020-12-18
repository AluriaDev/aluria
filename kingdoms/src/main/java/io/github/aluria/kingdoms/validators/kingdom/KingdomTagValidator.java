package io.github.aluria.kingdoms.validators.kingdom;

import io.github.aluria.kingdoms.validators.Validator;

import java.util.regex.Pattern;

public class KingdomTagValidator implements Validator<String> {

    private static final Pattern TAG_PATTERN = Pattern.compile("[a-zA-Z0-9]{2,4}");

    @Override
    public boolean validate(String value) {
        return TAG_PATTERN.matcher(value).matches();
    }
}
