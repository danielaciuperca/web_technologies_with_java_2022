package com.unibuc.ex1curs7.service;

import com.unibuc.ex1curs7.exception.BankAccountNotFoundException;
import com.unibuc.ex1curs7.model.BankAccount;
import com.unibuc.ex1curs7.model.BankAccountType;
import com.unibuc.ex1curs7.repository.BankAccountRepository;
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
        Optional<BankAccount> bankAccount = bankAccountRepository.getBy(id);
        if(bankAccount.isEmpty()) {
            throw new BankAccountNotFoundException(
                    "Bank account with id " + id + 
                            " attempted to be retrieved, but was not found.");
        }
        return bankAccount;
    }

    public List<BankAccount> getBy(BankAccountType type, Double balance) {
        return bankAccountRepository.getBy(type, balance);
    }
}
