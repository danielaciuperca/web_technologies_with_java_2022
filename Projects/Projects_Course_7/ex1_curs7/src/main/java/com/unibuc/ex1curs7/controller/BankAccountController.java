package com.unibuc.ex1curs7.controller;

import com.unibuc.ex1curs7.dto.BankAccountCreateRequest;
import com.unibuc.ex1curs7.mapper.BankAccountMapper;
import com.unibuc.ex1curs7.model.BankAccount;
import com.unibuc.ex1curs7.model.BankAccountType;
import com.unibuc.ex1curs7.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService,
                                 BankAccountMapper bankAccountMapper) {
        this.bankAccountService = bankAccountService;
        this.bankAccountMapper = bankAccountMapper;
    }

    @PostMapping
    public ResponseEntity<BankAccount> create(@RequestBody @Valid
                                                  BankAccountCreateRequest bankAccountCreateRequest) {
        BankAccount createdBankAccount = bankAccountService.create(
                bankAccountMapper.createRequestToModel(bankAccountCreateRequest)
        );
        return ResponseEntity
                .created(URI.create("/bankAccounts/" + createdBankAccount.getId()))
                .body(createdBankAccount);
    }

    @GetMapping("/{id}")
    public Optional<BankAccount> getBy(@PathVariable String id) {
        Optional<BankAccount> bankAccount = bankAccountService.getBy(id);
        return bankAccount;
    }

    @GetMapping
    public List<BankAccount> getBy(@RequestParam(required = false) BankAccountType type,
                                   @RequestParam(required = false) Double balance) {
        return bankAccountService.getBy(type, balance);
    }
}
