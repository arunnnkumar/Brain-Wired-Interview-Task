package com.brainwired.task.controller;

import com.brainwired.task.response.UserResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public UserResponse GlobalHandler(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            String field = bindingResult.getFieldError().getField();

            bindingResult
                    .getAllErrors()
                    .stream()
                    .forEach(er -> errors.put(((FieldError) er).getField(), er.getDefaultMessage()));
        }

//        System.out.println("reached handler");

        return UserResponse
                .<Map<String, String>>builder()
                .object(errors)
                .statusCode(400)
                .build();
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    public UserResponse DateFormatExceptionHandler() {
        return UserResponse
                .<Map<String, String>>builder()
                .message("Please check you date format. It should be 'yyyy-MM-dd'")
                .statusCode(400)
                .build();
    }


}
