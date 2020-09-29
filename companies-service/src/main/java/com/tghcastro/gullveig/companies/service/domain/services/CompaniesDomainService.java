package com.tghcastro.gullveig.companies.service.domain.services;

import com.tghcastro.gullveig.companies.service.domain.exceptions.CompanyNotFoundException;
import com.tghcastro.gullveig.companies.service.domain.interfaces.metrics.MetricsService;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.CompaniesRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.CompaniesService;
import com.tghcastro.gullveig.companies.service.domain.models.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesDomainService implements CompaniesService {

    private final CompaniesRepository companiesRepository;
    private final MetricsService metricsService;

    public CompaniesDomainService(CompaniesRepository companiesRepository, MetricsService metricsService) {
        this.metricsService = metricsService;
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
    public Optional<Company> getBySectorId(Long sectorId) {
        return this.companiesRepository.findBySectorId(sectorId);
    }

    @Override
    public Company create(Company companyToCreate) {
        Company createdCompany = this.companiesRepository.saveAndFlush(companyToCreate);
        if (createdCompany != null) {
            this.metricsService.registerCompanyCreated();
        }
        return createdCompany;
    }

    @Override
    public Company update(Long id, Company companyToUpdate) {
        Company existentCompany = this.getById(id).get();
        BeanUtils.copyProperties(companyToUpdate, existentCompany, "id");
        Company updatedCompany = this.companiesRepository.saveAndFlush(existentCompany);
        if (updatedCompany != null) {
            this.metricsService.registerCompanyUpdated();
        }
        return updatedCompany;
    }

    @Override
    public void delete(Long id) {
        Company companyToDelete = this.getById(id).get();
        companyToDelete.setEnabled(false);
        Company deletedCompany = this.companiesRepository.saveAndFlush(companyToDelete);
        if (deletedCompany != null) {
            this.metricsService.registerCompanyUpdated();
        }
    }
}
