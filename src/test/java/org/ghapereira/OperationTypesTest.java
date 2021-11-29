package org.ghapereira;

import org.ghapereira.domain.OperationTypes;
import org.ghapereira.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationTypesTest {
    @Test
    void operationTypeCodesAreCorrectlyAssigned() {
        Assertions.assertEquals(OperationTypes.SINGLE_INSTALLMENT.getOperationCode(), Constants.SINGLE_INSTALLMENT);
        Assertions.assertEquals(OperationTypes.MULTIPLE_INSTALLMENTS.getOperationCode(), Constants.MULTIPLE_INSTALLMENTS);
        Assertions.assertEquals(OperationTypes.CASH_WITHDRAWAL.getOperationCode(), Constants.CASH_WITHDRAWAL);
        Assertions.assertEquals(OperationTypes.PAYMENT.getOperationCode(), Constants.PAYMENT);
    }
}
