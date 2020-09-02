package com.rmm.rmmservices.exceptions;

/**
 * This class manages the DatabaseExceptions
 * @author Paul Rodríguez-Ch
 */
public class DatabaseException extends RuntimeException{

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
