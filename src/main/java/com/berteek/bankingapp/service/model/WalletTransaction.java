package com.berteek.bankingapp.service.model;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletTransaction {

    public static final String OPERATION_DEPOSIT = "DEPOSIT";
    public static final String OPERATION_WITHDRAW = "WITHDRAW";
    private static final BigDecimal MAX_AMOUNT = new BigDecimal(10_000_000);

    @NotNull(message = "Wallet ID must be specified")
    private UUID walletId;

    @NotBlank(message = "Operation type must be specified")
    @Pattern(regexp = "^(DEPOSIT|WITHDRAW)$", message = "Operation type must be either DEPOSIT or WITHDRAW")
    private String operationType;

    @NotNull(message = "Amount must be specified")
    @DecimalMin(value = "0.01", message = "Amount must be at least 0.01")
    @DecimalMax(value = "10000000.00", message = "Amount must be at most 10 000 000.00")
    @Digits(integer = 8, fraction = 2, message = "Amount must not have more than 8 integer and 2 fraction digits")
    private BigDecimal amount;

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isValid() {
        return amount != null
            && (OPERATION_DEPOSIT.equals(operationType) || OPERATION_WITHDRAW.equals(operationType))
            && amount.compareTo(BigDecimal.ZERO) > 0
            && amount.compareTo(MAX_AMOUNT) < 0;
    }
}
