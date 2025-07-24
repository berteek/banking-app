package com.berteek.bankingapp.api.controller;

import com.berteek.bankingapp.api.service.WalletService;
import com.berteek.bankingapp.service.model.Result;
import com.berteek.bankingapp.service.model.WalletTransaction;
import java.util.UUID;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
        logger.info("WalletController created");
    }

    @PostMapping("/wallet")
    public ResponseEntity<Result> makeWalletTransaction(@Valid @RequestBody WalletTransaction walletTransaction) {
        logger.info("Wallet transaction: {}", walletTransaction);

        if (!walletTransaction.isValid()) {
            logger.info("Wallet transaction is invalid");
            return ResponseEntity
                    .badRequest()
                    .body(new Result(Result.Status.FAILURE, "Invalid request body", null));
        }

        Result result;
        if (walletTransaction.getOperationType().equals(WalletTransaction.OPERATION_DEPOSIT)) {
            logger.info("Wallet transaction deposit");
            result = walletService.deposit(walletTransaction.getWalletId(), walletTransaction.getAmount());
        } else if (walletTransaction.getOperationType().equals(WalletTransaction.OPERATION_WITHDRAW)) {
            logger.info("Wallet transaction withdraw");
            result = walletService.withdraw(walletTransaction.getWalletId(), walletTransaction.getAmount());
        } else {
            logger.info("Wallet transaction not supported");
            return ResponseEntity
                    .internalServerError()
                    .body(new Result(Result.Status.FAILURE, "Internal server error", null));
        }

        if (result.getStatus().equals(Result.Status.FAILURE)) {
            logger.info("Wallet transaction failed");
            return ResponseEntity
                    .badRequest()
                    .body(result);
        }

        logger.info("Wallet transaction succeeded");
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/wallets/{id}")
    public ResponseEntity<Result> getWalletBalance(@PathVariable UUID id) {
        logger.info("Wallet transaction id: {}", id);
        Result result = walletService.findById(id);
        if (result.getStatus().equals(Result.Status.SUCCESS)) {
            logger.info("Wallet transaction successful");
            return ResponseEntity.ok().body(result);
        }
        logger.info("Wallet transaction failed");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}
