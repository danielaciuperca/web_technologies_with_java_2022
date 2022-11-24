package com.unibuc.ex1curs8.exception;

public class BankAccountNotFoundException extends RuntimeException {

    public BankAccountNotFoundException() {
        super("Bank account not found");
    }
}
