package com.Stock.demo.consumer;

import com.Stock.demo.Data.StockPriceHistory;
import com.Stock.demo.Data.UserAlertPreference;
import com.Stock.demo.Repository.StockPriceHistoryRepository;
import com.Stock.demo.Repository.UserAlertRepository;
import com.Stock.demo.Service.AlertService;
import com.Stock.demo.Service.PriceBufferService;
import com.Stock.demo.Service.StockCacheService;
import com.Stock.demo.Service.WebSocketAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaStockAlertConsumer {

    @Autowired
    private UserAlertRepository alertRepo;

    @Autowired
    private PriceBufferService priceBufferService;

    @Autowired
    private WebSocketAlertService webSocketAlertService;

    @Autowired
    private AlertService alertService;


    @Autowired
    private StockCacheService stockCacheService;

    @KafkaListener(topics = "stock-prices", groupId = "alert-group")
    public void consume(String message) {
        try {
            System.out.println("📥 Received from Kafka: " + message);

            // Example message: AAPL price is now $201.08
            String[] parts = message.split(" ");
            if (parts.length < 5) {
                System.err.println("❌ Malformed message: " + message);
                return;
            }

            String symbol = parts[0];
            double price = Double.parseDouble(parts[4].replace("$", ""));

            stockCacheService.updatePrice(symbol, price );
            priceBufferService.addPrice(symbol, price);

            // Fetch alert preferences and send emails if triggered
            List<UserAlertPreference> alerts = alertRepo.findBySymbol(symbol);
            for (UserAlertPreference user : alerts) {
                boolean trigger = user.getIsAboveThreshold()
                        ? price > user.getThreshold()
                        : price < user.getThreshold();

                if (trigger) {
                    System.out.println("✅ Trigger matched for user: " + user.getEmail());
                    alertService.sendNotification(user, symbol, price);
                    webSocketAlertService.sendAlert("🚨 " + symbol + " crossed $" + price + " for " + user.getEmail());
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Error in Kafka consumer: " + e.getMessage());
        }
    }
}
