package com.Stock.demo;

import com.Stock.demo.Service.KafkaStockProducer;
import com.Stock.demo.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StockScheduler {

    @Autowired
    private StockService stockService;

    @Autowired
    private KafkaStockProducer kafkaProducer;

    private final String[] symbols = {
            "AAPL", "GOOGL", "TSLA", "AMZN"
//            "MSFT", "NFLX", "META",
//            "BTC-USD", "ETH-USD", "DOGE-USD"
    };

    @Scheduled(fixedRate = 10000) // every 10 seconds
    public void fetchAndPublishPrices() {
        for (String symbol : symbols) {
            stockService.getPrice(symbol).subscribe(price -> {
                kafkaProducer.publishPrice(symbol, Double.parseDouble(price.getPrice()));
            });
        }
    }
}
