package org.sb_week4.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public NotFoundException() {
        message = "Not found!";
    }
}
