package com.draen.validation;

import com.draen.annotation.validation.LikeEither;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.core.env.Environment;


import java.util.List;

public class LikeEitherImpl implements ConstraintValidator<LikeEither, String> {
    private final Environment environment;
    private List<String> options;

    public LikeEitherImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext context) {
        return options.contains(string);
    }

    @Override
    public void initialize(LikeEither constraintAnnotation) {
        String[] optionsArray = constraintAnnotation.options();
        for (String option : optionsArray) {
            if (option.startsWith("${"))
                options.add(environment.resolveRequiredPlaceholders(option));
            else
                options.add(option);
        }
    }
}