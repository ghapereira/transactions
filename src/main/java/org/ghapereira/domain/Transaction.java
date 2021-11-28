package org.ghapereira.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="transaction")
public class Transaction extends PanacheEntity {
    private int operationTypeId;
    private float amount;
    private LocalDateTime eventDate;

    @ManyToOne(fetch=FetchType.LAZY, targetEntity=Account.class)
    @JoinColumn(name="account_id")
    @NotNull
    @JsonIgnore
    public Account account;

    @Transient
    public Long accountId;

    public int getOperationTypeId() {
        return this.operationTypeId;
    }

    public float getAmount() {
        return this.amount;
    }

    public LocalDateTime getEventDate() {
        return this.eventDate;
    }
}
