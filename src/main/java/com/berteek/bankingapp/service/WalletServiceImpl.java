package com.berteek.bankingapp.service;

import com.berteek.bankingapp.api.service.WalletService;
import com.berteek.bankingapp.service.model.TransactionResult;
import com.berteek.bankingapp.service.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletDao dao;

    @Autowired
    public WalletServiceImpl(WalletDao dao) {
        this.dao = dao;
    }

    @Override
    public Wallet findById(UUID id) {
        return Mapper.DataToService(dao.findById(id));
    }

    @Override
    public TransactionResult deposit(UUID id, BigDecimal amount) {
        Wallet wallet = Mapper.DataToService(dao.findById(id));
        if (wallet == null) {
            return new TransactionResult(TransactionResult.Status.FAILURE, "Could not find wallet");
        }
        wallet.setBalance(wallet.getBalance().add(amount));
        return new TransactionResult(TransactionResult.Status.SUCCESS, "Successfully deposited");
    }

    @Override
    public TransactionResult withdraw(UUID id, BigDecimal amount) {
        Wallet wallet = Mapper.DataToService(dao.findById(id));
        if (wallet == null) {
            return new TransactionResult(TransactionResult.Status.FAILURE, "Could not find wallet");
        }
        if (wallet.getBalance().compareTo(amount) < 0) {
            return new TransactionResult(TransactionResult.Status.FAILURE, "Insufficient balance");
        }
        wallet.setBalance(wallet.getBalance().subtract(amount));
        return new TransactionResult(TransactionResult.Status.SUCCESS, "Successfully withdrawn");
    }
}
