package com.springmvc.controller;

import com.springmvc.controller.exceptions.ErrorResponse;
import com.springmvc.controller.exceptions.WeatherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ErrorResponse> handleInternalServerError(Exception exception) {
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final String timestamp = Instant.now().toString();
        final int status = httpStatus.value();
        final String message = exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(message, timestamp, status);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler({WeatherNotFoundException.class})
    public final ResponseEntity<ErrorResponse> handleNotFoundException(WeatherNotFoundException exception) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        final String timestamp = Instant.now().toString();
        final int status = httpStatus.value();
        final String message = exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(message, timestamp, status);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
