package com.tghcastro.gullveig.stocks.service.repositories;

import com.tghcastro.gullveig.stocks.service.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StocksRepository extends JpaRepository<Stock,Long> {
    List<Stock> findAllByOrderByTickerAsc();
}