package org.ghapereira.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Entity;
import java.util.Optional;

@RegisterForReflection
@Entity(name="account")
public class Account extends PanacheEntity {
    private String documentNumber;

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public static Optional<Account> findByDocumentNumber(String documentNumber) {
        return Optional.ofNullable(find("documentNumber", documentNumber).firstResult());
    }
}
