package com.berteek.bankingapp.service;

import com.berteek.bankingapp.service.model.Wallet;

public class Mapper {

    public static com.berteek.bankingapp.data.model.Wallet ServiceToData(Wallet serviceWallet) {
        if (serviceWallet == null) {
            return null;
        }
        com.berteek.bankingapp.data.model.Wallet dataWallet = new com.berteek.bankingapp.data.model.Wallet();
        dataWallet.setId(serviceWallet.getId());
        dataWallet.setBalance(serviceWallet.getBalance());
        return dataWallet;
    }

    public static Wallet DataToService(com.berteek.bankingapp.data.model.Wallet dataWallet) {
        if (dataWallet == null) {
            return null;
        }
        Wallet serviceWallet = new Wallet();
        serviceWallet.setId(dataWallet.getId());
        serviceWallet.setBalance(dataWallet.getBalance());
        return serviceWallet;
    }
}
