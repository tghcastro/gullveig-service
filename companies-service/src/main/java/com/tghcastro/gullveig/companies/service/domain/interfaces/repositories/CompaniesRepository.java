package com.tghcastro.gullveig.companies.service.domain.interfaces.repositories;

import com.tghcastro.gullveig.companies.service.domain.models.Company;

import java.util.List;
import java.util.Optional;

public interface CompaniesRepository {
    Company saveAndFlush(Company existentCompany);

    Optional<Company> findById(Long id);

    List<Company> findAllByOrderByNameAsc();

    Optional<Company> findBySectorId(Long sectorId);
}
