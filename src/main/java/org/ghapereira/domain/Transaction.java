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
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="transaction")
public class Transaction extends PanacheEntity {
    @JsonProperty(required = true)
    private int operationTypeId;

    @JsonProperty(required = true)
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

    public void setOperationTypeId(int operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDateTime getEventDate() {
        return this.eventDate;
    }
}
