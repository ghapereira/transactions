package org.ghapereira;

import org.ghapereira.domain.Account;
import org.ghapereira.domain.OperationTypes;
import org.ghapereira.domain.Transaction;
import org.ghapereira.exceptions.BusinessException;
import org.ghapereira.repository.AccountRepository;
import org.ghapereira.repository.TransactionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TransactionService {
    @Inject
    TransactionRepository transactionRepository;

    @Inject
    AccountRepository accountRepository;

    public Transaction createTransaction(Transaction transaction) throws BusinessException {
        if (!OperationTypes.validCodes.contains(transaction.getOperationTypeId())) {
            throw new BusinessException("Invalid operationTypeId '" + transaction.getOperationTypeId() + "'");
        }

        Account existingAccount = accountRepository.findById(transaction.accountId);

        if (existingAccount == null) {
            throw new BusinessException("Account not found with id '" + transaction.accountId + "'");
        }

        transaction.account = existingAccount;
        transactionRepository.persist(transaction);

        return transaction;
    }

    public String modifyAccount(int id) {
        return "OK, " + id;
    }

    public String limitsTest() {
        return "OK, got limits";
    }
}
