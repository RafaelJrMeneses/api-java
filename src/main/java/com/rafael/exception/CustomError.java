package com.rafael.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public CustomError (int status, String error, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
