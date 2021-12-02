package org.ghapereira.repository;

import org.ghapereira.domain.Transaction;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {
    public List<Transaction> findByAccountNumber(Long accountNumber) {
        return find("account_id", accountNumber).list();
    }

}
