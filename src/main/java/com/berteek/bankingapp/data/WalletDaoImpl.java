package com.berteek.bankingapp.data;

import com.berteek.bankingapp.service.WalletDao;
import com.berteek.bankingapp.data.model.Wallet;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class WalletDaoImpl implements WalletDao {

    private final EntityManager entityManager;

    public WalletDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Wallet findById(UUID id) {
        return entityManager.find(Wallet.class, id);
    }
}
