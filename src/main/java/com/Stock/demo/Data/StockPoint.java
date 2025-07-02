package com.Stock.demo.Data;

import java.time.LocalDateTime;

public class StockPoint {
    private LocalDateTime timestamp;
    private double price;

    // Constructor + Getters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public StockPoint(LocalDateTime timestamp, double price) {
        this.timestamp = timestamp;
        this.price = price;
    }
}