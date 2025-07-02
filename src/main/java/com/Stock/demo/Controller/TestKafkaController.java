package com.Stock.demo.Controller;

import com.Stock.demo.Service.KafkaStockProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestKafkaController {

    @Autowired
    private KafkaStockProducer kafkaProducer;

    @GetMapping("/kafka")
    public ResponseEntity<String> testKafka() {
        kafkaProducer.publishPrice("AAPL", 123.45);
        return ResponseEntity.ok("Kafka test message sent.");
    }
}
