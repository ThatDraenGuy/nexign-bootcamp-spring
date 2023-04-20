package com.draen.annotation.validation;

import com.draen.validation.LikePatternImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LikePatternImpl.class)
@ReportAsSingleViolation
public @interface LikePattern {

    String regexp();

    String message() default "Value doesn't match pattern";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}