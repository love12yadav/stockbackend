package com.Stock.demo.Controller;

import com.Stock.demo.Service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestController {

    @Autowired
    private AlertService alertService;

    @GetMapping("/api/test-email")
    public String testEmail() {
        alertService.sendEmail("yadavlove2370@gmail.com", "Test from Spring", "✅ Email sent from Spring Boot");
        return "Email sent (check logs)";
    }
}
