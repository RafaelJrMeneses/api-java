package com.rafael.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomError> handlerPersonNotFoundException(PersonNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(
                status.value(),
                status.toString(),
                exception.getMessage()
        );

        return new ResponseEntity<CustomError>(err, status);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomError> handlerPersonAlreadyExistsException(PersonAlreadyExistsException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomError err = new CustomError(
                status.value(),
                status.toString(),
                exception.getMessage()
        );

        return new ResponseEntity<CustomError>(err, status);
    }

    @ExceptionHandler(PersonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomError> handlerPersonException(PersonException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(
                status.value(),
                status.toString(),
                exception.getMessage()
        );

        return new ResponseEntity<CustomError>(err, status);
    }
}
