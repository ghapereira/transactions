package org.ghapereira.domain;

public class Balance {
    private Long accountId;
    private float value;

    // TODO create a field for calculated time; maybe calculation id?

    public Balance(Long accountId, float value) {
        this.setAccountId(accountId);
        this.setValue(value);
    }

    // Keeping public setters for the JSON parsing lib
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return this.value;
    }

}
