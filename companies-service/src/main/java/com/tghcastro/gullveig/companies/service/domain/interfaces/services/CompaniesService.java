package com.tghcastro.gullveig.companies.service.domain.interfaces.services;

import com.tghcastro.gullveig.companies.service.domain.exceptions.CompanyNotFoundException;
import com.tghcastro.gullveig.companies.service.domain.exceptions.DomainException;
import com.tghcastro.gullveig.companies.service.domain.exceptions.DuplicatedCompanyNameException;
import com.tghcastro.gullveig.companies.service.domain.models.Company;

import java.util.List;
import java.util.Optional;

public interface CompaniesService {
    List<Company> getAll();

    Optional<Company> getById(Long id) throws CompanyNotFoundException;

    Optional<Company> getBySectorId(Long id) throws CompanyNotFoundException;

    Company create(Company companyToCreate) throws DomainException;

    Company update(Long id, Company companyToUpdate) throws CompanyNotFoundException, DuplicatedCompanyNameException;

    void delete(Long id) throws DomainException;

    Company addStock(Long companyId, String ticker);
}
