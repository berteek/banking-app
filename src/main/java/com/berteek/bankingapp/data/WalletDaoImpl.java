package com.berteek.bankingapp.data;

import com.berteek.bankingapp.service.WalletDao;
import com.berteek.bankingapp.data.model.Wallet;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class WalletDaoImpl implements WalletDao {

    private final EntityManager entityManager;

    @Autowired
    public WalletDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Wallet findById(UUID id) {
        return entityManager.find(Wallet.class, id);
    }

    @Override
    @Transactional
    public void save(Wallet wallet) {
        entityManager.persist(wallet);
    }
}
