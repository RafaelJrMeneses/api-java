package com.rafael.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException() {
        super("This person doesn't exist");
    }
}
