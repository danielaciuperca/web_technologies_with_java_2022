package com.unibuc.ex1curs7.exception;

public class BankAccountNotFoundException extends NotFoundException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
