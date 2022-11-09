package com.unibuc.ex1curs6.controller;

import com.unibuc.ex1curs6.model.BankAccount;
import com.unibuc.ex1curs6.model.BankAccountType;
import com.unibuc.ex1curs6.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired // implied
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    /*
        TODO
        1. create bank account
        2. retrieve bank account by id
        3. retrieve bank accounts list by type and / or balance
     */

    @PostMapping
    public ResponseEntity<BankAccount> create(@RequestBody BankAccount bankAccount) {
        BankAccount createdBankAccount = bankAccountService.create(bankAccount);
        return ResponseEntity
                .created(URI.create("/bankAccounts/" + createdBankAccount.getId()))
                .body(createdBankAccount);
    }

    @GetMapping("/{id}")
    public Optional<BankAccount> getBy(@PathVariable String id) { //same as @PathVariable(name = "id") String bankAccountId
        return bankAccountService.getBy(id);
    }

    @GetMapping
    public List<BankAccount> getBy(@RequestParam(required = false) BankAccountType type,
                                   @RequestParam(required = false) Double balance) {
        return bankAccountService.getBy(type, balance);
    }
}
