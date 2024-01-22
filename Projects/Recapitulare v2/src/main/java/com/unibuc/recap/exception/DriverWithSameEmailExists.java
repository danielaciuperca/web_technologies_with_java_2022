package com.unibuc.recap.exception;

public class DriverWithSameEmailExists extends RuntimeException {

    public DriverWithSameEmailExists() {
        super("A driver with the same email already exists");
    }
}
