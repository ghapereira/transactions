package org.ghapereira.services;

import org.ghapereira.domain.Account;
import org.ghapereira.exceptions.BusinessException;
import org.ghapereira.repository.AccountRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AccountService {
    @Inject
    AccountRepository accountRepository;

    public Account createAccount(Account account) throws BusinessException {
        if (account.getDocumentNumber() == null) {
            throw new BusinessException("Cannot create account without a valid document number");
        }

        accountRepository.persist(account);

        return account;
    }

    public Account getAccountInfo(Long id) throws BusinessException {
        Account account = accountRepository.findById(id);

        if (account == null) {
            throw new BusinessException("Account not found for id '" + id + "'");
        }

        return account;
    }
}
