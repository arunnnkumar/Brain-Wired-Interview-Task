package com.brainwired.task.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
public @interface DateFormat {

    String message() default "Invalid date format. Excepted format is 'yyyy/MM/dd'";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
