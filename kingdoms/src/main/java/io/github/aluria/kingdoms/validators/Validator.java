package io.github.aluria.kingdoms.validators;

public interface Validator<T> {

    boolean validate(T value);
}
