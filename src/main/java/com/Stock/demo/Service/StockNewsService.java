package com.Stock.demo.Service;

import com.Stock.demo.DTO.CompanyNewsDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class StockNewsService {

    private final WebClient webClient = WebClient.create("https://finnhub.io/api/v1");

    private final String API_KEY = "d1gcaihr01qmqatugku0d1gcaihr01qmqatugkug";

    public Mono<CompanyNewsDTO[]> getCompanyNews(String symbol, String from, String to) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/company-news")
                        .queryParam("symbol", symbol)
                        .queryParam("from", from)
                        .queryParam("to", to)
                        .queryParam("token", API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(CompanyNewsDTO[].class);
    }
}
