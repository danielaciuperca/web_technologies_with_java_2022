package com.unibuc.ex1curs11.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler({DuplicateDestinationException.class})
    public ResponseEntity handle(DuplicateDestinationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({DestinationNotFoundException.class})
    public ResponseEntity handle(DestinationNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
