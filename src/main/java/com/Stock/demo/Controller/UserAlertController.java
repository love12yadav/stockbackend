package com.Stock.demo.Controller;

import com.Stock.demo.Data.UserAlertPreference;
import com.Stock.demo.Repository.UserAlertRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class UserAlertController {

    @Autowired
    private UserAlertRepository alertRepo;

    // ✅ Add a new alert
    @PostMapping
    public UserAlertPreference addAlert(@RequestBody UserAlertPreference alert) {
        return alertRepo.save(alert);
    }

    // ✅ Get alerts by stock symbol
    @GetMapping("/symbol/{symbol}")
    public List<UserAlertPreference> getAlertsBySymbol(@PathVariable String symbol) {
        return alertRepo.findBySymbol(symbol);
    }

    // ✅ Delete alert by email + symbol + threshold
    @DeleteMapping("/delete")
    @Transactional
    public ResponseEntity<String> deleteAlert(@RequestParam String email,
                                              @RequestParam String symbol,
                                              @RequestParam Double threshold) {
        List<UserAlertPreference> alerts = alertRepo.findByEmailAndSymbolAndThreshold(email, symbol, threshold);

        if (alerts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        for (UserAlertPreference alert : alerts) {
            alertRepo.delete(alert);
        }

        return ResponseEntity.ok("Deleted successfully");
    }
}
