package com.tghcastro.gullveig.stocks.service.services;

import com.tghcastro.gullveig.stocks.service.exceptions.StockNotFoundException;
import com.tghcastro.gullveig.stocks.service.models.Stock;
import com.tghcastro.gullveig.stocks.service.repositories.StocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StocksServiceImpl implements StocksService {
    private final StocksRepository stocksRepository;

    @Override
    public List<Stock> getAll() {
        return this.stocksRepository.findAllByOrderByTickerAsc();
    }

    @Override
    public Optional<Stock> getById(Long id) {
        Optional<Stock> foundStock = this.stocksRepository.findById(id);
        if (!foundStock.isPresent()) {
            throw new StockNotFoundException(id);
        }
        return foundStock;
    }

    @Override
    public Stock create(Stock stockToCreate) {
        return this.stocksRepository.saveAndFlush(stockToCreate);
    }

    @Override
    public Stock update(Long id, Stock stockToUpdate) {
        Stock existentStock = this.getById(id).get();
        BeanUtils.copyProperties(stockToUpdate, existentStock, "id");
        return this.stocksRepository.saveAndFlush(existentStock);
    }

    @Override
    public void delete(Long id) {
        Stock stock = this.getById(id).get();
        stock.setEnabled(false);
        this.stocksRepository.saveAndFlush(stock);
    }
}
