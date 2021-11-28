package org.ghapereira.domain;

public enum OperationTypes {
    SINGLE_INSTALLMENT(1),
    MULTIPLE_INSTALLMENTS(2),
    CASH_WITHDRAWAL(3),
    PAYMENT(4);

    private final int operationCode;

    private OperationTypes(int operationCode) {
        this.operationCode = operationCode;
    }

    public int getOperationCode() {
        return this.operationCode;
    }

}
