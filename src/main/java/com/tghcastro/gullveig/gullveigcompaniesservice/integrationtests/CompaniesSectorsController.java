package com.tghcastro.gullveig.gullveigcompaniesservice.integrationtests;

import com.tghcastro.gullveig.gullveigcompaniesservice.models.CompaniesSector;
import com.tghcastro.gullveig.gullveigcompaniesservice.repositories.CompaniesSectorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public CompaniesSector create(@Valid @RequestBody CompaniesSector sector) {
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
