package com.rafael.exception;

import org.springframework.validation.FieldError;

public class PersonException extends RuntimeException {

    public PersonException(FieldError field) {
        super("The " + field.getField() + " field is incorrect");
    }
}
