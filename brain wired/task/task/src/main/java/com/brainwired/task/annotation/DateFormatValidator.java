package com.brainwired.task.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatValidator implements ConstraintValidator<DateFormat, LocalDate> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        System.out.println("reached deserializeer----------------------------");
        return value == null || validateFormat(value.toString());
    }

    private boolean validateFormat(String value) {
        try {
            LocalDate.parse(value, DATE_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
