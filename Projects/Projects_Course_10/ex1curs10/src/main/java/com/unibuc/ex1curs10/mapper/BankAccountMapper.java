package com.unibuc.ex1curs10.mapper;

import com.unibuc.ex1curs10.dto.BankAccountRequest;
import com.unibuc.ex1curs10.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccount bankAccountRequestToBankAccount(BankAccountRequest bankAccountRequest) {
        return new BankAccount(bankAccountRequest.getAccountNumber(), bankAccountRequest.getBalance(),
                bankAccountRequest.getType(), bankAccountRequest.getCardNumber());
    }
}
