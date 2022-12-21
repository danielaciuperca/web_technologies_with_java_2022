package com.unibuc.ex1curs10.controller;

import com.unibuc.ex1curs10.dto.BankAccountRequest;
import com.unibuc.ex1curs10.dto.TransferRequest;
import com.unibuc.ex1curs10.mapper.BankAccountMapper;
import com.unibuc.ex1curs10.model.*;
import com.unibuc.ex1curs10.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@Validated
@RequestMapping("/bankaccounts")
public class BankAccountController {

    private BankAccountService bankAccountService;
    private BankAccountMapper bankAccountMapper;

    public BankAccountController(BankAccountService bankAccountService, BankAccountMapper bankAccountMapper) {
        this.bankAccountService = bankAccountService;
        this.bankAccountMapper = bankAccountMapper;
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(
            @Valid
            @RequestBody BankAccountRequest bankAccountRequest) {
        BankAccount bankAccount = bankAccountMapper.bankAccountRequestToBankAccount(bankAccountRequest);
        BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount);
        return ResponseEntity
                .created(URI.create("/bankAccount/" + createdBankAccount.getId()))
                .body(createdBankAccount);
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccount(@PathVariable Long id) {
        return bankAccountService.getBankAccount(id);
    }

    @GetMapping
    public List<BankAccount> getAllByType(@RequestParam BankAccountType type) {
        return bankAccountService.getAllByType(type);
    }

    @GetMapping("/averagebalance/{type}")
    public double getBankAccountsAverageBalance(@PathVariable BankAccountType type) {
        return bankAccountService.getAverageBalance(type);
    }

    @PutMapping
    public void makeBankAccountTransfer(
            @Valid
            @RequestBody
            TransferRequest transferRequest) {
        bankAccountService.makeBankAccountTransfer(transferRequest);
    }
}
