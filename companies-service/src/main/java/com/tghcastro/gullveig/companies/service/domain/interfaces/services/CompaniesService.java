package com.tghcastro.gullveig.companies.service.domain.interfaces.services;

import com.tghcastro.gullveig.companies.service.domain.exceptions.CompanyNotFoundException;
import com.tghcastro.gullveig.companies.service.domain.exceptions.DomainException;
import com.tghcastro.gullveig.companies.service.domain.exceptions.DuplicatedCompanyNameException;
import com.tghcastro.gullveig.companies.service.domain.models.Company;
import com.tghcastro.gullveig.companies.service.domain.results.DomainResult;

import java.util.List;
import java.util.Optional;

public interface CompaniesService<T> {
    List<Company> getAll();

    Optional<T> getById(Long id) throws CompanyNotFoundException;

    Optional<T> getBySectorId(Long id) throws CompanyNotFoundException;

    DomainResult<T> create(Company companyToCreate) throws DomainException;

    DomainResult<T> update(Long id, Company companyToUpdate) throws CompanyNotFoundException, DuplicatedCompanyNameException;

    void delete(Long id) throws DomainException;

    Company addStock(Long companyId, String ticker);
}
