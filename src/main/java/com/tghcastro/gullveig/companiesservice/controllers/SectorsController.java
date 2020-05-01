package com.tghcastro.gullveig.companiesservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tghcastro.gullveig.companiesservice.exceptions.SectorNotFoundException;
import com.tghcastro.gullveig.companiesservice.models.Sector;
import com.tghcastro.gullveig.companiesservice.repositories.SectorsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/companiessectors")
public class SectorsController {

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

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        companiesSectorRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Sector update(@PathVariable Long id, @RequestBody Sector sector) {
        Sector existentSector = companiesSectorRepository.getOne(id);
        BeanUtils.copyProperties(sector, existentSector, "id");
        return companiesSectorRepository.saveAndFlush(existentSector);
    }

    // TODO: Improve error handling
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String responseBody = mapper.writeValueAsString(errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody);
    }
}
