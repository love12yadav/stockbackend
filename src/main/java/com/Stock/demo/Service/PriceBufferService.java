package com.Stock.demo.Service;
import com.Stock.demo.Data.StockPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PriceBufferService {

    private final Map<String, Deque<StockPoint>> priceMap = new ConcurrentHashMap<>();

    public void addPrice(String symbol, double price) {
        priceMap.putIfAbsent(symbol, new LinkedList<>());
        Deque<StockPoint> deque = priceMap.get(symbol);

        if (deque.size() > 50) deque.removeFirst(); // Keep only last 50 points
        deque.addLast(new StockPoint(LocalDateTime.now(), price));
    }

    public List<StockPoint> getRecentPrices(String symbol) {
        return new ArrayList<>(priceMap.getOrDefault(symbol, new LinkedList<>()));
    }
}

