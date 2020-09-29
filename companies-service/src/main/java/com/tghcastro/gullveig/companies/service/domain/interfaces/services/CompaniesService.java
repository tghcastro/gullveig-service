package com.tghcastro.gullveig.companies.service.domain.interfaces.services;

import com.tghcastro.gullveig.companies.service.domain.models.Company;

import java.util.List;
import java.util.Optional;

public interface CompaniesService {
    List<Company> getAll();

    Optional<Company> getById(Long id);

    Optional<Company> getBySectorId(Long id);

    Company create(Company companyToCreate);

    Company update(Long id, Company companyToUpdate);

    void delete(Long id);
}