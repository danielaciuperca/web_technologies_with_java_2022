package com.unibuc.ex1curs10.model;

import javax.persistence.*;

@Entity
@Table(name = "bankaccounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_number")
    private String accountNumber;

    private double balance;

    @Enumerated(EnumType.STRING)
    private BankAccountType type;

    @Column(name = "card_number")
    private String cardNumber;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, double balance, BankAccountType type, String cardNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.cardNumber = cardNumber;
    }

    public BankAccount(long id, String accountNumber, double balance, BankAccountType type, String cardNumber) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.cardNumber = cardNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
