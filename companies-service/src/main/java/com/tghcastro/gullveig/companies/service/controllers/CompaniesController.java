package com.tghcastro.gullveig.companies.service.controllers;

import com.tghcastro.gullveig.companies.service.models.Company;
import com.tghcastro.gullveig.companies.service.services.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompaniesController {
    @Autowired
    private CompaniesService companiesService;

    @GetMapping
    public List<Company> list() {
        return companiesService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Company get(@PathVariable Long id) {
        return companiesService.getById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@Valid @RequestBody Company company) {
        return companiesService.create(company);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        companiesService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Company update(@PathVariable Long id, @RequestBody Company company) {
        return companiesService.update(id, company);
    }
}
