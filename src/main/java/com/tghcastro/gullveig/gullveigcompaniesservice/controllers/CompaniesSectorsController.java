package com.tghcastro.gullveig.gullveigcompaniesservice.controllers;

import com.tghcastro.gullveig.gullveigcompaniesservice.models.CompaniesSector;
import com.tghcastro.gullveig.gullveigcompaniesservice.repositories.CompaniesSectorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companiessectors")
public class CompaniesSectorsController {

    @Autowired
    public CompaniesSectorRepository companiesSectorRepository;

    @GetMapping
    public List<CompaniesSector> list() {
        return companiesSectorRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public CompaniesSector get(@PathVariable Long id) {
        return companiesSectorRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompaniesSector create(@RequestBody CompaniesSector sector) {
        return companiesSectorRepository.saveAndFlush(sector);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        companiesSectorRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public CompaniesSector update(@PathVariable Long id, @RequestBody CompaniesSector sector) {
        CompaniesSector existentSector = companiesSectorRepository.getOne(id);
        BeanUtils.copyProperties(sector, existentSector, "id");
        return companiesSectorRepository.saveAndFlush(existentSector);
    }
}
