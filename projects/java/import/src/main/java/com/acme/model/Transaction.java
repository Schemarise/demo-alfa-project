package com.acme.model;

import java.math.BigDecimal;

public class Transaction {
    private java.util.UUID id;
    private java.util.Date when;
    private String description;
    private BigDecimal amount;
    private Transfer transferRequest;

    public java.util.UUID getId() {
        return id;
    }
    public void setId(java.util.UUID id) {
        this.id = id;
    }

    public java.util.Date getWhen() {
        return when;
    }
    public void setWhen(java.util.Date when) {
        this.when = when;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Transfer getTransferRequest() {
        return transferRequest;
    }
    public void setTransferRequest(Transfer transferRequest) {
        this.transferRequest = transferRequest;
    }

}
