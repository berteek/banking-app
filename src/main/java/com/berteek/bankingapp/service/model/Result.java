package com.berteek.bankingapp.service.model;

public class Result {

    public enum Status { SUCCESS, FAILURE }
    private final Status status;
    private final String message;
    private final Wallet wallet;

    public Result(Status status, String message, Wallet wallet) {
        this.status = status;
        this.message = message;
        this.wallet = wallet;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
