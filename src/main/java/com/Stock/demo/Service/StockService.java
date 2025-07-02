package com.Stock.demo.Service;

import com.Stock.demo.DTO.StockPrice;
import com.Stock.demo.DTO.StockChartData;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class StockService {

    private final WebClient webClient = WebClient.create("https://finnhub.io/api/v1");
    private final String API_KEY = "d1gcaihr01qmqatugku0d1gcaihr01qmqatugkug";

    public Mono<StockPrice> getPrice(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/quote")
                        .queryParam("symbol", symbol)
                        .queryParam("token", API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    StockPrice price = new StockPrice();
                    price.setSymbol(symbol);
                    price.setPrice(json.get("c").asText());
                    return price;
                });
    }

    public Mono<StockChartData> getHistoricalData(String symbol) {
        long now = Instant.now().getEpochSecond();
        long from = now - (60 * 30); // last 30 minutes

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stock/candle")
                        .queryParam("symbol", symbol)
                        .queryParam("resolution", "1")
                        .queryParam("from", from)
                        .queryParam("to", now)
                        .queryParam("token", API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(StockChartData.class);
    }
}
