package org.ghapereira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountCreatorEntity {

    public String document_number;

    // An empty constructor is needed for Jackson to be able to serialize
    public AccountCreatorEntity() {}

    public AccountCreatorEntity(String document_number) {
        this.document_number = document_number;
    }
}
