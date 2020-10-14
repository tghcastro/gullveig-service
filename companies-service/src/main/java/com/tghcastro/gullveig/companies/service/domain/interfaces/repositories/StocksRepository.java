package com.tghcastro.gullveig.companies.service.domain.interfaces.repositories;

import com.tghcastro.gullveig.companies.service.domain.models.Stock;

public interface StocksRepository {
    Stock saveAndFlush(Stock stock);
}
