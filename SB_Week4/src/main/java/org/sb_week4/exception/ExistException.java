package org.sb_week4.exception;

import org.springframework.http.HttpStatus;

public class ExistException extends RuntimeException {
    private String message ;
    private HttpStatus httpStatus;

    public ExistException() {
        message = "Account existed";
    }
}
