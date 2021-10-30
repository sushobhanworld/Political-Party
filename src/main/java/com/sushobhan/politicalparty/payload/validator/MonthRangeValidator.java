package com.sushobhan.politicalparty.payload.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class MonthRangeValidator implements ConstraintValidator<MonthRange, Integer> {
    List<Integer> months = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return months.contains(value);
    }
}
