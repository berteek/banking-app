package com.berteek.bankingapp.service;

import com.berteek.bankingapp.api.service.WalletService;
import com.berteek.bankingapp.service.model.Result;
import com.berteek.bankingapp.service.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletDao dao;

    @Autowired
    public WalletServiceImpl(WalletDao dao) {
        this.dao = dao;
    }

    @Cacheable(value = "wallets", key = "#id", unless = "#result.status == T(com.berteek.bankingapp.service.model.Result$Status).FAILURE")
    @Transactional
    @Override
    public Result findById(UUID id) {
        Wallet wallet = Mapper.DataToService(dao.findById(id));
        if (wallet == null) {
            return new Result(Result.Status.FAILURE, "Could not find wallet", null);
        }
        return new Result(Result.Status.SUCCESS, "Found wallet", wallet);
    }

    @CacheEvict(value = "wallets", key = "#id")
    @Transactional
    @Override
    public Result deposit(UUID id, BigDecimal amount) {
        com.berteek.bankingapp.data.model.Wallet dataWallet = dao.findById(id);
        Wallet wallet = Mapper.DataToService(dataWallet);
        if (wallet == null) {
            return new Result(Result.Status.FAILURE, "Could not find wallet", null);
        }

        wallet.setBalance(wallet.getBalance().add(amount));
        dataWallet.setBalance(wallet.getBalance());

        dao.save(dataWallet);

        return new Result(Result.Status.SUCCESS, "Successfully deposited", wallet);
    }

    @CacheEvict(value = "wallets", key = "#id")
    @Override
    public Result withdraw(UUID id, BigDecimal amount) {
        com.berteek.bankingapp.data.model.Wallet dataWallet = dao.findById(id);
        Wallet wallet = Mapper.DataToService(dataWallet);
        if (wallet == null) {
            return new Result(Result.Status.FAILURE, "Could not find wallet", null);
        }
        if (wallet.getBalance().compareTo(amount) < 0) {
            return new Result(Result.Status.FAILURE, "Insufficient balance", wallet);
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        dataWallet.setBalance(wallet.getBalance());

        dao.save(dataWallet);

        return new Result(Result.Status.SUCCESS, "Successfully withdrawn", wallet);
    }
}
