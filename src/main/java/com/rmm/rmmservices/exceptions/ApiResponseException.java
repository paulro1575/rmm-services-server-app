package com.rmm.rmmservices.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * This class manages some API Exceptions
 * @author Paul Rodríguez-Ch
 */
public class ApiResponseException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime time;

    public ApiResponseException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = ZonedDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTime() {
        return time;
    }
}
