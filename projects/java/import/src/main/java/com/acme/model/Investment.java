package com.acme.model;

import com.acme.model.governance.Sensitive;

import java.time.LocalDateTime;

import static com.acme.model.governance.DataSensitivity.High;

public class Investment {
    private String stockSymbol;
    private double price;
    private LocalDateTime executed;
    private int quantity;

    @Sensitive(DataSensitivity=High)
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
