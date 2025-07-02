package com.Stock.demo.Data;

import jakarta.persistence.*;
@Entity
@Table(name = "user_alerts")
public class UserAlertPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String phone;
    private String symbol;
    private Double threshold;
    private Boolean isAboveThreshold;

    // ✅ Required by JPA
    public UserAlertPreference() {
    }

    // All-args constructor
    public UserAlertPreference(Long id, String email, String phone, String symbol, Double threshold, Boolean isAboveThreshold) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.symbol = symbol;
        this.threshold = threshold;
        this.isAboveThreshold = isAboveThreshold;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Boolean getIsAboveThreshold() {
        return isAboveThreshold;
    }

    public void setAboveThreshold(Boolean isAboveThreshold) {
        this.isAboveThreshold = isAboveThreshold;
    }
}
