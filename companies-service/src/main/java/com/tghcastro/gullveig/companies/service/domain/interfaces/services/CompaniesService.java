package com.tghcastro.gullveig.companies.service.domain.interfaces.services;

import com.tghcastro.gullveig.companies.service.domain.models.Company;
import com.tghcastro.gullveig.companies.service.domain.results.DomainResult;

import java.util.List;
import java.util.Optional;

public interface CompaniesService<T> {
    List<Company> getAll();

    Optional<T> getById(Long id);

    Optional<T> getBySectorId(Long id);

    DomainResult<T> create(Company companyToCreate);

    DomainResult<T> update(Long id, Company companyToUpdate);

    DomainResult<T> delete(Long id);

    DomainResult<T> addStock(Long companyId, String ticker);
}
