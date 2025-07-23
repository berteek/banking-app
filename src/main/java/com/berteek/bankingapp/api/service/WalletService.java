package com.berteek.bankingapp.api.service;

import com.berteek.bankingapp.service.model.TransactionResult;
import com.berteek.bankingapp.service.model.Wallet;

import java.util.UUID;

public interface WalletService {

    public Wallet findById(UUID id);

    public TransactionResult deposit(UUID id, Integer amount);
    public TransactionResult withdraw(UUID id, Integer amount);
}
