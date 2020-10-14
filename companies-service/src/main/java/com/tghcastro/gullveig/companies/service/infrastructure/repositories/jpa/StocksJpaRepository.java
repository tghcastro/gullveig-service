package com.tghcastro.gullveig.companies.service.infrastructure.repositories.jpa;

import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.StocksRepository;
import com.tghcastro.gullveig.companies.service.domain.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StocksJpaRepository extends JpaRepository<Stock, Long>, StocksRepository {
}
