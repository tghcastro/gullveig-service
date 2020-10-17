package com.tghcastro.gullveig.companies.service.application.controllers;

import com.tghcastro.gullveig.companies.service.domain.exceptions.DomainException;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.CompaniesService;
import com.tghcastro.gullveig.companies.service.domain.models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompaniesController {
    @Autowired
    private CompaniesService<Company> companiesService;

    @GetMapping
    public List<Company> list() {
        return companiesService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Company get(@PathVariable Long id) throws DomainException {
        return companiesService.getById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@Valid @RequestBody Company company) {
        return companiesService.create(company).onSuccessReturnValue();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws DomainException {
        companiesService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Company update(@PathVariable Long id, @RequestBody Company company) {
        return companiesService.update(id, company).onSuccessReturnValue();
    }

    @RequestMapping(value = "{id}/{ticker}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Company addStock(@PathVariable Long id, @PathVariable String ticker) {
        return companiesService.addStock(id, ticker).onSuccessReturnValue();
    }
}
