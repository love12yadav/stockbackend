package com.Stock.demo.Service;

import com.Stock.demo.Data.UserAlertPreference;
import com.Stock.demo.Repository.UserAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AlertService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserAlertRepository alertRepo;

    // ⏱ Map to store last alert times per (email + symbol)
    private final ConcurrentHashMap<String, LocalDateTime> alertTimestamps = new ConcurrentHashMap<>();

    public void sendNotification(UserAlertPreference user, String symbol, double currentPrice) {
        System.out.println("📨 Preparing to send email to: " + user.getEmail());

        String key = user.getEmail() + "_" + symbol;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastSent = alertTimestamps.getOrDefault(key, LocalDateTime.MIN);

        // ⏳ Only send if 30 minutes have passed
        if (lastSent.plusMinutes(30).isBefore(now)) {
            String message = "ALERT: " + symbol + " price is " + currentPrice
                    + " which has " + (user.getIsAboveThreshold() ? "exceeded" : "fallen below")
                    + " the threshold of " + user.getThreshold();

            sendEmail(user.getEmail(), "📈 Stock Alert: " + symbol, message);
            alertTimestamps.put(key, now); // ⏰ update last sent time
            System.out.println("📧 Email sent to " + user.getEmail() + ": " + message);
        } else {
            System.out.println("⏳ Skipping email for " + key + " (30 min not passed)");
        }
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }

    public void sendSms(String phone, String body) {
        // Optional SMS logic (placeholder)
        System.out.println("📲 (SMS to " + phone + "): " + body);
    }

    /**
     * Delete alert by email, symbol, and threshold.
     * This method requires a transaction.
     */
    @Transactional
    public void deleteAlert(String email, String symbol, Double threshold) {
        List<UserAlertPreference> alerts = alertRepo.findByEmailAndSymbolAndThreshold(email, symbol, threshold);
        if (alerts.isEmpty()) {
            System.out.println("❌ No matching alerts found to delete.");
        }
        for (UserAlertPreference alert : alerts) {
            alertRepo.delete(alert);
            System.out.println("✅ Deleted alert: " + alert.getEmail() + " " + alert.getSymbol() + " " + alert.getThreshold());
        }
    }
}
