package com.berteek.bankingapp.api.controller;

import com.berteek.bankingapp.api.service.WalletService;
import com.berteek.bankingapp.service.model.Result;
import com.berteek.bankingapp.service.model.Wallet;
import com.berteek.bankingapp.service.model.WalletTransaction;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Result makeWalletTransaction(@RequestBody WalletTransaction walletTransaction) {
        if (walletTransaction.getOperationType().equals(WalletTransaction.OPERATION_DEPOSIT)) {
            return walletService.deposit(walletTransaction.getWalletId(), walletTransaction.getAmount());
        } else if (walletTransaction.getOperationType().equals(WalletTransaction.OPERATION_WITHDRAW)) {
            return walletService.withdraw(walletTransaction.getWalletId(), walletTransaction.getAmount());
        }
        return new Result(Result.Status.FAILURE, "Invalid operation type", null);
    }

    @GetMapping("/wallets/{id}")
    public Result getWalletBalance(@PathVariable UUID id) {
        return walletService.findById(id);
    }
}
