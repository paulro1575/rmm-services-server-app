package com.rmm.rmmservices.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * This exception is used into the authorization process
 * @author Paul Rodríguez-Ch
 */
public class AuthenticationException extends Throwable {

    String message;
    HttpStatus httpStatus;
    ZonedDateTime time;


    public AuthenticationException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = ZonedDateTime.now();
    }


    @Override
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
