package com.Stock.demo.Service;

import com.Stock.demo.Data.UserAlertPreference;
import com.Stock.demo.Repository.UserAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AlertTriggerService {

    @Autowired
    private UserAlertRepository alertRepo;

    @Autowired
    private StockService stockService;

    @Scheduled(fixedRate = 30000) // Every 30 seconds
    public void checkAlerts() {
        List<UserAlertPreference> alerts = alertRepo.findAll();

        // Group alerts by symbol to avoid duplicate API calls
        Map<String, List<UserAlertPreference>> groupedBySymbol = alerts.stream()
                .collect(Collectors.groupingBy(UserAlertPreference::getSymbol));

        for (String symbol : groupedBySymbol.keySet()) {
            List<UserAlertPreference> symbolAlerts = groupedBySymbol.get(symbol);

            stockService.getPrice(symbol).subscribe(price -> {
                try {
                    double currentPrice = Double.parseDouble(price.getPrice());

                    for (UserAlertPreference alert : symbolAlerts) {
                        if (alert.getIsAboveThreshold() && currentPrice > alert.getThreshold()) {
                            System.out.println("🔔 ALERT: " + symbol + " is ABOVE " + alert.getThreshold()
                                    + " → Current Price: " + currentPrice
                                    + " | Notifying " + alert.getEmail());
                        } else if (!alert.getIsAboveThreshold() && currentPrice < alert.getThreshold()) {
                            System.out.println("🔔 ALERT: " + symbol + " is BELOW " + alert.getThreshold()
                                    + " → Current Price: " + currentPrice
                                    + " | Notifying " + alert.getEmail());
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid price received for " + symbol + ": " + price.getPrice());
                }
            });
        }
    }
}

