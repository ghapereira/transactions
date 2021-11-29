package org.ghapereira.services;

import org.ghapereira.domain.Account;
import org.ghapereira.domain.OperationTypes;
import org.ghapereira.domain.Transaction;
import org.ghapereira.exceptions.BusinessException;
import org.ghapereira.repository.AccountRepository;
import org.ghapereira.repository.TransactionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.time.LocalDateTime;

@ApplicationScoped
public class TransactionService {
    @Inject
    TransactionRepository transactionRepository;

    @Inject
    AccountRepository accountRepository;

    public Transaction createTransaction(Transaction transaction) throws BusinessException {
        this.validateOperation(transaction);

        Account existingAccount = accountRepository.findById(transaction.accountId);

        if (existingAccount == null) {
            throw new BusinessException("Account not found with id '" + transaction.accountId + "'");
        }

        transaction.account = existingAccount;

        transaction.setEventDate(LocalDateTime.now());
        transactionRepository.persist(transaction);

        return transaction;
    }

    private void validateOperation(Transaction transaction) throws BusinessException {
        int operationTypeId = transaction.getOperationTypeId();

        if (!OperationTypes.validCodes.contains(operationTypeId)) {
            throw new BusinessException("Invalid operationTypeId '" + operationTypeId + "'");
        }

        boolean isNegativeCode = OperationTypes.negativeCodes.contains(operationTypeId);
        boolean isInvalidNegative = isNegativeCode && transaction.getAmount() > 0;
        if (isInvalidNegative) {
            throw new BusinessException("Operation type " + operationTypeId + " requires a negative amount");
        }

        boolean isPositiveCode = OperationTypes.positiveCodes.contains(operationTypeId);
        boolean isInvalidPositive = isPositiveCode && transaction.getAmount() < 0;
        if (isInvalidPositive) {
            throw new BusinessException("Operation type " + operationTypeId + " requires a positive amount");
        }
    }
}
