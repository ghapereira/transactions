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
import java.util.List;

@ApplicationScoped
public class TransactionService {
    @Inject
    TransactionRepository transactionRepository;

    @Inject
    AccountRepository accountRepository;

    public Transaction createTransaction(Transaction transaction) throws BusinessException {
        this.validateOperation(transaction);

        if (OperationTypes.negativeCodes.contains(transaction.getOperationTypeId())) {
            float currentAmount = transaction.getAmount();
            transaction.setAmount(-currentAmount);
        }

        Account existingAccount = accountRepository.findById(transaction.accountId);

        if (existingAccount == null) {
            throw new BusinessException("Account not found with id '" + transaction.accountId + "'");
        }

        transaction.account = existingAccount;

        this.validateAndProcessTransaction(transaction);

        transaction.setEventDate(LocalDateTime.now());
        transactionRepository.persist(transaction);

        return transaction;
    }

    private void validateOperation(Transaction transaction) throws BusinessException {
        if (transaction.getAmount() < 0) {
            throw new BusinessException("Transaction amounts should always be positive");
        }
    }

    public List<Transaction> getAccountTransactions(Long accountId) {
        return transactionRepository.findByAccountNumber(accountId);
    }

    private void validateAndProcessTransaction(Transaction transaction) throws BusinessException {
        float operationResult = transaction.account.getAvailableCreditLimit() + transaction.getAmount();
        if (operationResult < 0) {
            throw new BusinessException("Transaction cannot be completed due to lack of funds");
        }

        transaction.account.changeCreditLimit(transaction.getAmount());
    }
}
