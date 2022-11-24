package com.unibuc.ex1curs8.controller;

import com.unibuc.ex1curs8.dto.BankAccountRequest;
import com.unibuc.ex1curs8.mapper.BankAccountMapper;
import com.unibuc.ex1curs8.model.BankAccount;
import com.unibuc.ex1curs8.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/bankAccounts")
public class BankAccountController {

    private BankAccountService bankAccountService;
    private BankAccountMapper bankAccountMapper;

    public BankAccountController(BankAccountService bankAccountService, BankAccountMapper bankAccountMapper) {
        this.bankAccountService = bankAccountService;
        this.bankAccountMapper = bankAccountMapper;
    }

    @PostMapping
    public ResponseEntity<String> createBankAccount(
            @Valid
            @RequestBody BankAccountRequest bankAccountRequest) {
        BankAccount bankAccount = bankAccountMapper.bankAccountRequestToBankAccount(bankAccountRequest);
        bankAccountService.createBankAccount(bankAccount);
        return ResponseEntity
                .created(null)
                .build();
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccount(@PathVariable Long id) {
        return bankAccountService.getBankAccount(id);
    }
}
