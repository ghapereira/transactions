package org.ghapereira.domain;

import org.ghapereira.utils.Constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum OperationTypes {
    SINGLE_INSTALLMENT(Constants.SINGLE_INSTALLMENT),
    MULTIPLE_INSTALLMENTS(Constants.MULTIPLE_INSTALLMENTS),
    CASH_WITHDRAWAL(Constants.CASH_WITHDRAWAL),
    PAYMENT(Constants.PAYMENT);

    private final int operationCode;

    public static final Set<Integer> validCodes = new HashSet<Integer>(Arrays.asList(
        Constants.SINGLE_INSTALLMENT,
        Constants.MULTIPLE_INSTALLMENTS,
        Constants.CASH_WITHDRAWAL,
        Constants.PAYMENT
    ));

    private OperationTypes(int operationCode) {
        this.operationCode = operationCode;
    }

    public int getOperationCode() {
        return this.operationCode;
    }

}
