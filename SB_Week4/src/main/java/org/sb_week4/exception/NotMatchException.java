package org.sb_week4.exception;

import org.springframework.http.HttpStatus;

public class NotMatchException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public NotMatchException() {
        message = "Password doesn't match!";
    }
}
