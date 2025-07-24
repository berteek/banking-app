package com.berteek.bankingapp;

import com.berteek.bankingapp.api.controller.WalletController;
import com.berteek.bankingapp.api.service.WalletService;
import com.berteek.bankingapp.service.model.Result;
import com.berteek.bankingapp.service.model.Wallet;
import com.berteek.bankingapp.service.model.WalletTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WalletControllerTest {

    @Mock
    private WalletService walletService;

    @InjectMocks
    private WalletController walletController;

    @Test
    public void testMakeWalletTransaction_DepositSuccess() {
        WalletTransaction transaction = new WalletTransaction();
        UUID id = UUID.randomUUID();
        transaction.setWalletId(id);
        transaction.setAmount(BigDecimal.valueOf(100.0));
        transaction.setOperationType(WalletTransaction.OPERATION_DEPOSIT);

        Wallet expectedWallet = new Wallet();
        expectedWallet.setId(id);
        expectedWallet.setBalance(BigDecimal.valueOf(150.0));
        Result expectedResult = new Result(Result.Status.SUCCESS, "Deposit successful", expectedWallet);
        when(walletService.deposit(any(UUID.class), any(BigDecimal.class))).thenReturn(expectedResult);

        ResponseEntity<Result> response = walletController.makeWalletTransaction(transaction);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testMakeWalletTransaction_InvalidRequest() {
        WalletTransaction transaction = new WalletTransaction();
        transaction.setWalletId(null);
        transaction.setAmount(BigDecimal.valueOf(-10.0));
        transaction.setOperationType(WalletTransaction.OPERATION_DEPOSIT);

        ResponseEntity<Result> response = walletController.makeWalletTransaction(transaction);

        assertEquals(400, response.getStatusCode().value());
        assertEquals(Result.Status.FAILURE, response.getBody().getStatus());
    }

    @Test
    public void testGetWalletBalance_Success() {
        UUID walletId = UUID.randomUUID();
        Wallet expectedWallet = new Wallet();
        expectedWallet.setId(walletId);
        expectedWallet.setBalance(BigDecimal.valueOf(100.0));
        Result expectedResult = new Result(Result.Status.SUCCESS, "Wallet found", expectedWallet);

        when(walletService.findById(walletId)).thenReturn(expectedResult);

        ResponseEntity<Result> response = walletController.getWalletBalance(walletId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testGetWalletBalance_NotFound() {
        UUID walletId = UUID.randomUUID();
        Result expectedResult = new Result(Result.Status.FAILURE, "Wallet not found", null);

        when(walletService.findById(walletId)).thenReturn(expectedResult);

        ResponseEntity<Result> response = walletController.getWalletBalance(walletId);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(expectedResult, response.getBody());
    }
}
