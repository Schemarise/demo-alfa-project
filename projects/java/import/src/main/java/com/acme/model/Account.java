package com.acme.model;

public class Account {
    private java.util.List<Transaction> latestTransactions;

    public java.util.List<Transaction> getLatestTransactions() {
        return latestTransactions;
    }

    public void setLatestTransactions(java.util.List<Transaction> latestTransactions) {
        this.latestTransactions = latestTransactions;
    }    
}
