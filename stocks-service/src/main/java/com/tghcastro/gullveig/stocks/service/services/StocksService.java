package com.tghcastro.gullveig.stocks.service.services;

import com.tghcastro.gullveig.stocks.service.models.Stock;

import java.util.List;
import java.util.Optional;

public interface StocksService {
    List<Stock> getAll();

    Optional<Stock> getById(Long id);

    Stock create(Stock stockToCreate);

    Stock update(Long id, Stock stockToUpdate);

    void delete(Long id);
}
