package org.ghapereira.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.Set;

@Entity(name="account")
public class Account extends PanacheEntity {
    private String documentNumber;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private Set<Transaction> transactions;

    public String getDocumentNumber() {
        return this.documentNumber;
    }
}
