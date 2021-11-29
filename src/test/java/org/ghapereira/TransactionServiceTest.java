package org.ghapereira;

import org.ghapereira.domain.OperationTypes;
import org.ghapereira.domain.Transaction;
import org.ghapereira.exceptions.BusinessException;
import org.ghapereira.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionServiceTest {
    @Test
    void cannotCreateTransactionWithInexistentOperationType() {
        Transaction sampleTransaction = new Transaction();
        sampleTransaction.setOperationTypeId(0);

        Assertions.assertThrows(
            BusinessException.class,
            () -> {new TransactionService().createTransaction(sampleTransaction);}
        );
    }

    @Test
    void paymentOperationCannotHaveNegativeAmounts() {
        Transaction sampleTransaction = new Transaction();
        sampleTransaction.setOperationTypeId(OperationTypes.PAYMENT.getOperationCode());
        sampleTransaction.setAmount(-10);

        Assertions.assertThrows(
            BusinessException.class,
            () -> {new TransactionService().createTransaction(sampleTransaction);}
        );
    }

    @Test
    void singleInstallmentOperationCannotHavePositiveAmounts() {
        Transaction sampleTransaction = new Transaction();
        sampleTransaction.setOperationTypeId(OperationTypes.SINGLE_INSTALLMENT.getOperationCode());
        sampleTransaction.setAmount(10);

        Assertions.assertThrows(
            BusinessException.class,
            () -> {new TransactionService().createTransaction(sampleTransaction);}
        );
    }

    @Test
    void multipleInstallmentOperationCannotHavePositiveAmounts() {
        Transaction sampleTransaction = new Transaction();
        sampleTransaction.setOperationTypeId(OperationTypes.MULTIPLE_INSTALLMENTS.getOperationCode());
        sampleTransaction.setAmount(10);

        Assertions.assertThrows(
            BusinessException.class,
            () -> {new TransactionService().createTransaction(sampleTransaction);}
        );
    }

    @Test
    void withdrawalOperationCannotHavePositiveAmounts() {
        Transaction sampleTransaction = new Transaction();
        sampleTransaction.setOperationTypeId(OperationTypes.CASH_WITHDRAWAL.getOperationCode());
        sampleTransaction.setAmount(10);

        Assertions.assertThrows(
            BusinessException.class,
            () -> {new TransactionService().createTransaction(sampleTransaction);}
        );
    }

    // TODO use mock to test successful creation

}
