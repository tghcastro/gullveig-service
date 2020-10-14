package com.tghcastro.gullveig.companies.service.domain.services;

import com.tghcastro.gullveig.companies.service.domain.exceptions.CompanyNotFoundException;
import com.tghcastro.gullveig.companies.service.domain.exceptions.DuplicatedCompanyNameException;
import com.tghcastro.gullveig.companies.service.domain.interfaces.metrics.MetricsService;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.CompaniesRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.StocksRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.CompaniesService;
import com.tghcastro.gullveig.companies.service.domain.models.Company;
import com.tghcastro.gullveig.companies.service.domain.models.Stock;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesDomainService implements CompaniesService {

    private final CompaniesRepository companiesRepository;
    private final StocksRepository stocksRepository;
    private final MetricsService metricsService;

    public CompaniesDomainService(CompaniesRepository companiesRepository, StocksRepository stocksRepository, MetricsService metricsService) {
        this.stocksRepository = stocksRepository;
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
        Optional<Company> foundCompany = this.companiesRepository.findBySectorId(sectorId);
        if (!foundCompany.isPresent()) {
            return Optional.empty();
        }
        return foundCompany;
    }

    @Override
    public Company create(Company companyToCreate) {
        companyToCreate.validate();
        Company alreadyExistentCompany = this.companiesRepository.findByName(companyToCreate.getName());

        if (alreadyExistentCompany != null) {
            throw new DuplicatedCompanyNameException(alreadyExistentCompany, companyToCreate);
        }

        Company createdCompany = this.companiesRepository.saveAndFlush(companyToCreate);

        companyToCreate.getStocks().forEach(this.stocksRepository::saveAndFlush);

        if (createdCompany != null) {
            this.metricsService.registerCompanyCreated();
        }
        return createdCompany;
    }

    @Override
    public Company addStock(Long companyId, String ticker) {
        Company company = this.getById(companyId).get();
        Stock stock = new Stock(ticker);
        company.addStock(this.stocksRepository.saveAndFlush(stock));
        return internalUpdate(company);
    }

    @Override
    public Company update(Long id, Company companyToUpdate) {
        companyToUpdate.validate();
        Company existentCompany = this.getById(id).get();
        BeanUtils.copyProperties(companyToUpdate, existentCompany, "id");
        return internalUpdate(existentCompany);
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

    private Company internalUpdate(Company companyToUpdate) {
        Company alreadyExistentCompany = this.companiesRepository.findByName(companyToUpdate.getName());

        if (alreadyExistentCompany != null && !alreadyExistentCompany.getId().equals(companyToUpdate.getId())) {
            throw new DuplicatedCompanyNameException(alreadyExistentCompany, companyToUpdate);
        }

        Company updatedCompany = this.companiesRepository.saveAndFlush(companyToUpdate);
        if (updatedCompany != null) {
            this.metricsService.registerCompanyUpdated();
        }
        return updatedCompany;
    }
}
