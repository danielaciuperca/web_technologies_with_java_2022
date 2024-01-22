package com.unibuc.ex1curs7.mapper;

import com.unibuc.ex1curs7.dto.BankAccountCreateRequest;
import com.unibuc.ex1curs7.model.BankAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    
//    public BankAccount createRequestToModel(BankAccountCreateRequest bankAccountCreateRequest) {
//        return new BankAccount(bankAccountCreateRequest.getAccountNumber(),
//                bankAccountCreateRequest.getBalance(), 
//                bankAccountCreateRequest.getType(),
//                bankAccountCreateRequest.getCardNumber());
//    }
    
    BankAccount createRequestToModel(BankAccountCreateRequest bankAccountCreateRequest);
}
