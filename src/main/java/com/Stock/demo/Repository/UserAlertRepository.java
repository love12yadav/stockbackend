package com.Stock.demo.Repository;

import com.Stock.demo.Data.UserAlertPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAlertRepository extends JpaRepository<UserAlertPreference, Long> {
    List<UserAlertPreference> findBySymbol(String symbol);


    void deleteByEmailAndSymbolAndThreshold(String email, String symbol, double threshold);

    List<UserAlertPreference> findByEmailAndSymbolAndThreshold(String email, String symbol, Double threshold);
}
