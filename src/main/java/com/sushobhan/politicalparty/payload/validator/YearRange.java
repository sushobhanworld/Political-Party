package com.sushobhan.politicalparty.payload.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = YearRangeValidator.class)
@Documented
public @interface YearRange {
    String message() default "Year must be in the range of 2020 to 2040";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
