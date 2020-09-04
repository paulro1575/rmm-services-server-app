package com.rmm.rmmservices.exceptions;

import com.rmm.rmmservices.utils.ResponseEntityHeaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

/**
 * This class handles API exceptions
 * @author Paul Rodríguez-Ch
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * Handles the Database Exceptions
     * @param databaseException The database exception
     * @return ResponseEntity JSON
     */
    @ExceptionHandler(value = {DatabaseException.class})
    public ResponseEntity<Object> handleDatabaseException(DatabaseException databaseException) {
        ApiResponseException apiResponseException = new ApiResponseException(databaseException.getMessage(),
                HttpStatus.BAD_REQUEST);
        LOGGER.warn(databaseException.getMessage());
        return new ResponseEntity<>(apiResponseException, ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                apiResponseException.getHttpStatus());
    }

    /**
     * Hanldles the no Such Element Exception
     * @param noSuchElementException the noSuchElementException
     * @return ResponseEntity JSON
     */
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleValidationException(NoSuchElementException noSuchElementException) {
        ApiResponseException apiResponseException = new ApiResponseException(noSuchElementException.getMessage(),
                HttpStatus.NOT_FOUND);
        LOGGER.warn(noSuchElementException.getMessage());
        return new ResponseEntity<>(apiResponseException, ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                apiResponseException.getHttpStatus());
    }

    /**
     * Hanldles any other Exception
     * @param exception Any exception
     * @return ResponseEntity JSON
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyOtherException(Exception exception) {
        ApiResponseException apiResponseException = new ApiResponseException(exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<>(apiResponseException, ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                apiResponseException.getHttpStatus());
    }
}
