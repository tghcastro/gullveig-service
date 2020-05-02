package com.tghcastro.gullveig.companiesservice.services;

import com.tghcastro.gullveig.companiesservice.models.Company;

import java.util.List;
import java.util.Optional;

public interface CompaniesService {
    List<Company> getAll();

    Optional<Company> getById(Long id);

    Company create(Company companyToCreate);

    Company update(Long id, Company companyToUpdate);

    void delete(Long id);
}
