package org.ghapereira.services;

import org.ghapereira.domain.Balance;
import org.ghapereira.domain.Transaction;
import org.ghapereira.exceptions.BusinessException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BalanceService {
    @Inject
    TransactionService transactionService;

    @Inject
    AccountService accountService;

    public Balance calculateBalance(Long accountId) throws BusinessException {
        try {
            accountService.getAccountInfo(accountId);
        } catch (BusinessException b) {
            // TODO Log that account could not be found, so we cannot look for transactions for them
            throw new BusinessException("No account with id '" + accountId + "'");
        }

        List<Transaction> accountTransactons = transactionService.getAccountTransactions(accountId);

        float balanceAmount = 0;

        for(Transaction transaction : accountTransactons) {
            balanceAmount += transaction.getAmount();
        }

        return new Balance(accountId, balanceAmount);
    }
}
