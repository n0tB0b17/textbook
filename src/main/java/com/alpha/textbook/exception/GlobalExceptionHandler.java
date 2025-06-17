package com.alpha.textbook.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage(),
                req.getDescription(false),
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDetail> handleUserAlreadyExists(
            UserAlreadyExistsException ex,
            WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage(),
                req.getDescription(false),
                HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LocationAlreadyExistsException.class)
    public ResponseEntity<ErrorDetail> handleLocationAlreadyExists(
            LocationAlreadyExistsException ex,
            WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage(),
                req.getDescription(false),
                HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }
}
