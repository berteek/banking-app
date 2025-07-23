package com.berteek.bankingapp.service.model;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletTransaction {

    public static final String OPERATION_DEPOSIT = "DEPOSIT";
    public static final String OPERATION_WITHDRAW = "WITHDRAW";

    private UUID walletId;

    private String operationType;

    private BigDecimal amount;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
