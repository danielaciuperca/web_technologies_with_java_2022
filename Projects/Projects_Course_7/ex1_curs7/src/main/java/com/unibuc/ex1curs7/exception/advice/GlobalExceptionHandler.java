package com.unibuc.ex1curs7.exception.advice;

import com.unibuc.ex1curs7.exception.BankAccountNotFoundException;
import com.unibuc.ex1curs7.exception.CustomerNotFoundException;
import com.unibuc.ex1curs7.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> handle(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage() + 
                        " Timestamp: " + LocalDateTime.now());
    }
    
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getBindingResult().getFieldError().getField() + 
                        " " + e.getMessage());
    }
}
