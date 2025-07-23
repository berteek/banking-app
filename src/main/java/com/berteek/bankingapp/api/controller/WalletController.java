package com.berteek.bankingapp.api.controller;

import com.berteek.bankingapp.api.service.WalletService;
import com.berteek.bankingapp.service.model.TransactionResult;
import com.berteek.bankingapp.service.model.Wallet;
import com.berteek.bankingapp.service.model.WalletTransaction;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong!!!!!";
    }

    @PostMapping("/wallet")
    public TransactionResult makeWalletTransaction(@RequestBody WalletTransaction walletTransaction) {
        return walletService.withdraw(walletTransaction.getWalletId(), walletTransaction.getAmount());
    }

    @GetMapping("/wallets/{id}")
    public Wallet getWalletBalance(@PathVariable UUID id) {
        return walletService.findById(id);
    }
}
