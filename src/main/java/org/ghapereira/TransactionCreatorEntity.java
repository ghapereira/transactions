package org.ghapereira;

import javax.transaction.Transaction;

public class TransactionCreatorEntity {

    // TODO can I map raw types in the ORM?
    public int accountId;
    public int operationTypeId;
    public float amount;

    public TransactionCreatorEntity() {}

    public TransactionCreatorEntity(int accountId, int operationTypeId, float amount) {
        this.accountId = accountId;
        this.operationTypeId = operationTypeId;
        this.amount = amount;
    }

}
