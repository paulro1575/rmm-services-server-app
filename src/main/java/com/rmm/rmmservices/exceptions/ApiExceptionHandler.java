package com.rmm.rmmservices.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

/**
 * @author Paul Rodríguez-Ch
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleApiException(Exception exception) throws Exception {
        ApiResponseException apiResponseException = new ApiResponseException(exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);;

        if(exception instanceof DatabaseException){
            apiResponseException = new ApiResponseException(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
            LOGGER.error(exception.getMessage());
            return new ResponseEntity<>(apiResponseException, apiResponseException.getHttpStatus());

        } else if(exception instanceof ConstraintViolationException || exception instanceof PSQLException) {

            apiResponseException = new ApiResponseException("JSON bad format was detected: " + exception.getMessage() ,
                    HttpStatus.BAD_REQUEST);
            LOGGER.error(exception.getMessage());
            return new ResponseEntity<>(apiResponseException, apiResponseException.getHttpStatus());

        } else if (exception instanceof NoSuchElementException) {

            apiResponseException = new ApiResponseException("Element not found: " + exception.getMessage() ,
                    HttpStatus.NOT_FOUND);
            LOGGER.error(exception.getMessage());
            return new ResponseEntity<>(apiResponseException, apiResponseException.getHttpStatus());
        }
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<>(apiResponseException, apiResponseException.getHttpStatus());
    }
}
