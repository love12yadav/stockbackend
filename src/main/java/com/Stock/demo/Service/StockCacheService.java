package com.Stock.demo.Service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// StockCacheService.java
@Service
public class StockCacheService {
    private final Map<String, Double> latestPrices = new ConcurrentHashMap<>();

    public void updatePrice(String symbol, double price) {
        latestPrices.put(symbol, price);
    }

    public Map<String, Double> getAllPrices() {
        return latestPrices;
    }

    public Double getPrice(String symbol) {
        return latestPrices.get(symbol);
    }
}
