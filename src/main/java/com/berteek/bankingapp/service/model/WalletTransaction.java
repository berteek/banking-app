package com.berteek.bankingapp.service.model;

import java.util.UUID;

public class WalletTransaction {

    public static final String OPERATION_DEPOSIT = "DEPOSIT";
    public static final String OPERATION_WITHDRAW = "WITHDRAW";

    private UUID walletId;

    private String operationType;

    private Integer amount;

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
