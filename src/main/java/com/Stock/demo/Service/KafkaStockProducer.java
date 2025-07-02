package com.Stock.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaStockProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishPrice(String symbol, double price) {
        String message = symbol + " price is now $" + price;
        kafkaTemplate.send("stock-prices", message);
        System.out.println("📤 Kafka message sent: " + message);
    }
}
