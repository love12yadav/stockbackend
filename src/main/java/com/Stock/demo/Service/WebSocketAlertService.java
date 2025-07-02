package com.Stock.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketAlertService {
    @Autowired
    private SimpMessagingTemplate template;

    public void sendAlert(String message) {
        template.convertAndSend("/topic/alerts", message);
    }
}
