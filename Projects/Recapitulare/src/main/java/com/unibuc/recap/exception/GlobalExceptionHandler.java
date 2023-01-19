package com.unibuc.recap.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DriverAlreadyExistsException.class,
            MethodArgumentNotValidException.class})
    public ResponseEntity handle(Exception exception) {
        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }

}
