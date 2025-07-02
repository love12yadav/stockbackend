package com.Stock.demo.Controller;

import com.Stock.demo.DTO.CompanyNewsDTO;
import com.Stock.demo.Service.StockNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/api/news")
public class StockNewsController {

    @Autowired
    private StockNewsService stockNewsService;

    @GetMapping("/{symbol}")
    public Mono<ResponseEntity<CompanyNewsDTO[]>> getNews(
            @PathVariable String symbol,
            @RequestParam String from,
            @RequestParam String to) {

        return stockNewsService.getCompanyNews(symbol, from, to)
                .map(news -> ResponseEntity.ok().body(news))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}