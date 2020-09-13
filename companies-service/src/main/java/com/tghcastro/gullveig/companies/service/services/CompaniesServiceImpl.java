package com.tghcastro.gullveig.companies.service.services;

import com.tghcastro.gullveig.companies.service.exceptions.CompanyNotFoundException;
import com.tghcastro.gullveig.companies.service.models.Company;
import com.tghcastro.gullveig.companies.service.repositories.CompaniesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesServiceImpl implements CompaniesService {

    private final CompaniesRepository companiesRepository;

    public CompaniesServiceImpl(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Override
    public List<Company> getAll() {
        return this.companiesRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Optional<Company> getById(Long id) {
        Optional<Company> foundCompany = this.companiesRepository.findById(id);
        if (!foundCompany.isPresent()) {
            throw new CompanyNotFoundException(id);
        }
        return foundCompany;
    }

    @Override
    public Company create(Company companyToCreate) {
        return this.companiesRepository.saveAndFlush(companyToCreate);
    }

    @Override
    public Company update(Long id, Company companyToUpdate) {
        Company existentCompany = this.getById(id).get();
        BeanUtils.copyProperties(companyToUpdate, existentCompany, "id");
        return this.companiesRepository.saveAndFlush(existentCompany);
    }

    @Override
    public void delete(Long id) {
        Company companyToDelete = this.getById(id).get();
        companyToDelete.setEnabled(false);
        this.companiesRepository.saveAndFlush(companyToDelete);
    }
}
