package com.unibuc.ex1curs10.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransferRequest {
    @NotNull
    private long fromBankAccountId;
    @NotNull
    private long toBankAccountId;
    @NotNull
    @Min(0)
    private double amount;

    public TransferRequest() {
    }

    public TransferRequest(long fromBankAccountId, long toBankAccountId, double amount) {
        this.fromBankAccountId = fromBankAccountId;
        this.toBankAccountId = toBankAccountId;
        this.amount = amount;
    }

    public long getFromBankAccountId() {
        return fromBankAccountId;
    }

    public void setFromBankAccountId(long fromBankAccountId) {
        this.fromBankAccountId = fromBankAccountId;
    }

    public long getToBankAccountId() {
        return toBankAccountId;
    }

    public void setToBankAccountId(long toBankAccountId) {
        this.toBankAccountId = toBankAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
