package com.draen.validation;

import com.draen.annotation.validation.LikeDateTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LikeDateTimeImpl implements ConstraintValidator<LikeDateTime, String> {
    private final Environment environment;
    private DateTimeFormatter formatter;

    public LikeDateTimeImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext context) {
        try {
            LocalDateTime.parse(string, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public void initialize(LikeDateTime constraintAnnotation) {
        String pattern = constraintAnnotation.pattern();
        if (pattern.startsWith("${"))
            formatter = DateTimeFormatter.ofPattern(environment.resolveRequiredPlaceholders(pattern));
        else
            formatter = DateTimeFormatter.ofPattern(pattern);
    }
}