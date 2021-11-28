package org.ghapereira.repository;

import org.ghapereira.domain.Account;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {
    public Account findByDocumentNumber(String documentNumber) {
        return find("documentNumber", documentNumber).firstResult();
    }

    public Account findById(Long id) {
        return find("id", id).firstResult();
    }
 }
