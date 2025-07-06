package ru.job4j.rservice.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.xml.ws.WebServiceException;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.job4j.rservice.exception.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(constraintViolation -> constraintViolation.getPropertyPath()
                .forEach(error -> errors.put(constraintViolation.getMessage(), error.getName())));
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                errors.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(SerializationFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleSerializationFailedException(SerializationFailedException exception) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleIOException(IOException exception) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(WebServiceException.class)
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public ErrorResponse handleWebServiceException(WebServiceException exception) {
        return new ErrorResponse(HttpStatus.UPGRADE_REQUIRED.value(), exception.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleServiceException(ServiceException exception) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}