package com.unibuc.recap.exception;

public class DriverAlreadyExistsException extends RuntimeException {

    public DriverAlreadyExistsException() {
        super("A driver with the same email already exists.");
    }
}
