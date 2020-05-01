package com.tghcastro.gullveig.gullveigcompaniesservice.controllers;

import com.tghcastro.gullveig.gullveigcompaniesservice.models.Sector;
import com.tghcastro.gullveig.gullveigcompaniesservice.exceptions.SectorNotFoundException;
import com.tghcastro.gullveig.gullveigcompaniesservice.repositories.SectorsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/companiessectors")
public class SectorsController extends ResponseEntityExceptionHandler {

    @Autowired
    public SectorsRepository companiesSectorRepository;

    @GetMapping
    public List<Sector> list() {
        return companiesSectorRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Sector get(@PathVariable Long id) {
        return companiesSectorRepository
                .findById(id)
                .orElseThrow(() -> new SectorNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sector create(@Valid @RequestBody Sector sector) {
        return companiesSectorRepository.saveAndFlush(sector);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        companiesSectorRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Sector update(@PathVariable Long id, @RequestBody Sector sector) {
        Sector existentSector = companiesSectorRepository.getOne(id);
        BeanUtils.copyProperties(sector, existentSector, "id");
        return companiesSectorRepository.saveAndFlush(existentSector);
    }
}
