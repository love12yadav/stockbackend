package com.Stock.demo.Controller;

import com.Stock.demo.Service.StockCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// PriceController.java
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private StockCacheService stockCacheService;

    @GetMapping
    public Map<String, Double> getAllPrices() {
        return stockCacheService.getAllPrices();
    }
}

