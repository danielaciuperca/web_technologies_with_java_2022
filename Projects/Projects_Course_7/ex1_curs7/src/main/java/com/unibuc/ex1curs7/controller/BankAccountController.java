package com.unibuc.ex1curs7.controller;

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

    @Autowired // implied
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccount> create(@RequestBody @Valid BankAccount bankAccount) {
        //TODO Wrap the request's body into a wrapper class (e.g. BankAccountRequest.class)
        //TODO   and use the wrapper class to validate input and map it to the model (BankAccount.class)
        BankAccount createdBankAccount = bankAccountService.create(bankAccount);
        return ResponseEntity
                .created(URI.create("/bankAccounts/" + createdBankAccount.getId()))
                .body(createdBankAccount);
    }

    @GetMapping("/{id}")
    public Optional<BankAccount> getBy(@PathVariable String id) { //same as @PathVariable(name = "id") String bankAccountId
        Optional<BankAccount> bankAccount = bankAccountService.getBy(id);
        return bankAccount;
    }

    @GetMapping
    public List<BankAccount> getBy(@RequestParam(required = false) BankAccountType type,
                                   @RequestParam(required = false) Double balance) {
        return bankAccountService.getBy(type, balance);
    }

    //Moved method to ControllerAdvice to reuse it for multiple controllers
    /*@ExceptionHandler
    public ResponseEntity<String> handle(IllegalStateException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage() + " at " + LocalDateTime.now());
    }*/
}
