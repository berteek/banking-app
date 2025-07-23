package com.berteek.bankingapp.api.service;

import com.berteek.bankingapp.service.model.Result;
import com.berteek.bankingapp.service.model.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {

    public Result findById(UUID id);

    public Result deposit(UUID id, BigDecimal amount);
    public Result withdraw(UUID id, BigDecimal amount);
}
