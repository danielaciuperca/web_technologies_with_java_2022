package com.unibuc.ex1curs7.repository;

import com.unibuc.ex1curs7.model.BankAccount;
import com.unibuc.ex1curs7.model.BankAccountType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class BankAccountRepository {
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount create(BankAccount bankAccount) {
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public Optional<BankAccount> getBy(String id) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getId().equals(id))
                .findFirst();
    }

    public List<BankAccount> getBy(BankAccountType type, Double balance) {
        return bankAccounts.stream()
                .filter(bankAccount -> type == null || bankAccount.getType().equals(type))
                .filter(bankAccount -> balance == null || Double.valueOf(bankAccount.getBalance()).equals(balance.doubleValue()))
                .collect(Collectors.toList());
    }
}
