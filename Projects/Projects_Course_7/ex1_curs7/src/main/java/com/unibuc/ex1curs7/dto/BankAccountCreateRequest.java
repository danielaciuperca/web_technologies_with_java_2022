package com.unibuc.ex1curs7.dto;

import com.unibuc.ex1curs7.model.BankAccountType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BankAccountCreateRequest {
    
    @NotEmpty
    private String accountNumber;
    @Min(0)
    private double balance;
    @NotNull
    private BankAccountType type;
    @Pattern(regexp = "^4[0-9]{12}(?:[0-9]{3})?$")
    private String cardNumber;

    public BankAccountCreateRequest() {
    }

    public BankAccountCreateRequest(String accountNumber, double balance, BankAccountType type, String cardNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public BankAccountType getType() {
        return type;
    }

    public void setType(BankAccountType type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
