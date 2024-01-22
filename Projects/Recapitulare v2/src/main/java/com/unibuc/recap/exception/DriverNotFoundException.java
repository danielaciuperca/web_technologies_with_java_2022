package com.unibuc.recap.exception;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException() {
        super("The driver does not exist.");
    }
}
