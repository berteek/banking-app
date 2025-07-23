package com.berteek.bankingapp.service;

import com.berteek.bankingapp.data.model.Wallet;

import java.util.UUID;

public interface WalletDao {

    public Wallet findById(UUID id);
}
