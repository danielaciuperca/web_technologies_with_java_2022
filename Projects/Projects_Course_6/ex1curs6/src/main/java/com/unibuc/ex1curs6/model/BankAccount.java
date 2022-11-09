package com.unibuc.ex1curs6.model;

public class BankAccount {
    private String id;
    private String accountNumber;
    private double balance;
    private BankAccountType type;
    private String cardNumber;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, double balance, BankAccountType type, String cardNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.cardNumber = cardNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
