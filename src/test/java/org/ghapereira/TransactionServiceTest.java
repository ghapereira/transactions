package org.ghapereira;

import org.ghapereira.Constants;
import org.ghapereira.domain.OperationTypes;
import org.ghapereira.domain.Transaction;
import org.ghapereira.exceptions.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionServiceTest {
    @Test
    void cannotCreateTransactionWithInexistentOperationType() {
        Transaction sampleTransaction = new Transaction();
        sampleTransaction.setOperationTypeId(0);

        Assertions.assertThrows(
            BusinessException.class,
            () -> {new TransactionService().createTransaction(sampleTransaction);},
            "Invalid operation code '0'"
        );
    }

    // TODO use mock to test successful creation

}
