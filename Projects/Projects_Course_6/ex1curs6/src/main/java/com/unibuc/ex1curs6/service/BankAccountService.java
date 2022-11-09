package com.unibuc.ex1curs6.service;

import com.unibuc.ex1curs6.model.BankAccount;
import com.unibuc.ex1curs6.model.BankAccountType;
import com.unibuc.ex1curs6.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount create(BankAccount bankAccount) {
        return bankAccountRepository.create(bankAccount);
    }

    public Optional<BankAccount> getBy(String id) {
        return bankAccountRepository.getBy(id);
    }

    public List<BankAccount> getBy(BankAccountType type, Double balance) {
        return bankAccountRepository.getBy(type, balance);
    }
}
