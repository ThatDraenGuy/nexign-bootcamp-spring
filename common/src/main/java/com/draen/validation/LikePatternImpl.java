package com.draen.validation;

import com.draen.annotation.validation.LikePattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.core.env.Environment;

import java.util.regex.Pattern;

public class LikePatternImpl implements ConstraintValidator<LikePattern, String> {
    private final Environment environment;
    private String patternString;

    public LikePatternImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(patternString);
        return pattern.matcher(string).matches();
    }

    @Override
    public void initialize(LikePattern constraintAnnotation) {
        String regexp = constraintAnnotation.regexp();
        if (regexp.startsWith("${"))
            patternString = environment.resolveRequiredPlaceholders(regexp);
        else
            patternString = regexp;
    }
}