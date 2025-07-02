package com.Stock.demo.Repository;


import com.Stock.demo.Data.StockPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistory, Long> {
    List<StockPriceHistory> findBySymbolOrderByTimestampDesc(String symbol);
}
