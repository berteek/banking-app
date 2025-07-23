package com.berteek.bankingapp.service.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {

    private UUID id;

    private BigDecimal balance;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
