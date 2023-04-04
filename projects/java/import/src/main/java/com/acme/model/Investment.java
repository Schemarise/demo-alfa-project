package com.acme.model;

import java.time.LocalDateTime;

public class Investment {
    private String stockSymbol;
    private double price;
    private LocalDateTime executed;
    private int quantity;

    public String getStockSymbol() {
        return stockSymbol;
    }
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public LocalDateTime getExecuted() {
        return executed;
    }
    public void setExecuted(LocalDateTime executed) {
        this.executed = executed;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
