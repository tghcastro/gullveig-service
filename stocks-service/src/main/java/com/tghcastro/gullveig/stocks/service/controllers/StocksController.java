package com.tghcastro.gullveig.stocks.service.controllers;

import com.tghcastro.gullveig.stocks.service.models.Stock;
import com.tghcastro.gullveig.stocks.service.services.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/stocks")
public class StocksController {
    @Autowired
    private StocksService stocksService;

    @GetMapping
    public List<Stock> list() {
        return stocksService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Stock get(@PathVariable Long id) {
        return stocksService.getById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Stock create(@Valid @RequestBody Stock company) {
        return stocksService.create(company);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stocksService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Stock update(@PathVariable Long id, @RequestBody Stock company) {
        return stocksService.update(id, company);
    }
}
