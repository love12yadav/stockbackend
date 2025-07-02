package com.Stock.demo.Controller;

import com.Stock.demo.DTO.StockChartData;
import com.Stock.demo.DTO.StockPrice;
import com.Stock.demo.Service.StockService;
import com.Stock.demo.Service.PriceBufferService;
import com.Stock.demo.Data.StockPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private PriceBufferService priceBufferService;

    @GetMapping("/price/{symbol}")
    public Mono<StockPrice> getStockPrice(@PathVariable String symbol) {
        return stockService.getPrice(symbol);
    }

    // ✅ New endpoint to get recent prices for graphing
    @GetMapping("/price/history/{symbol}")
    public List<StockPoint> getRecentPrices(@PathVariable String symbol) {
        return priceBufferService.getRecentPrices(symbol);
    }
}
