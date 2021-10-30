package com.sushobhan.politicalparty.payload.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class YearRangeValidator implements ConstraintValidator<YearRange, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 2020 && value <= 2040;
    }
}
