package com.unibuc.ex1curs8.service;

import com.unibuc.ex1curs8.exception.BankAccountNotFoundException;
import com.unibuc.ex1curs8.model.BankAccount;
import com.unibuc.ex1curs8.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountService {
    private BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void createBankAccount(BankAccount bankAccount) {
        bankAccountRepository.createBankAccount(bankAccount);
    }

    public BankAccount getBankAccount(Long id) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.getBankAccount(id);
        if(bankAccountOptional.isPresent()) {
            return bankAccountOptional.get();
        } else {
            throw new BankAccountNotFoundException();
        }
    }
}
