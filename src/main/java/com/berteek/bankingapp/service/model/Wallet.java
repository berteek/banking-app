package com.berteek.bankingapp.service.model;

import java.util.UUID;

public class Wallet {

    private UUID id;

    private Integer balance;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
