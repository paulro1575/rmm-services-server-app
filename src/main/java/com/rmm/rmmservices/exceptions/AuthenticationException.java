package com.rmm.rmmservices.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class AuthenticationException extends Throwable {

    String message;
    HttpStatus httpStatus;
    ZonedDateTime time;

    public AuthenticationException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = ZonedDateTime.now();
    }

    public AuthenticationException() {
    }

    public AuthenticationException(String s) {
        super(s);
    }

    public AuthenticationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }

    public AuthenticationException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
