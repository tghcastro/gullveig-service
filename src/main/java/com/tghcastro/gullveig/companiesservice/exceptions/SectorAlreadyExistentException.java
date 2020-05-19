package com.tghcastro.gullveig.companiesservice.exceptions;

import com.tghcastro.gullveig.companiesservice.models.Sector;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class SectorAlreadyExistentException extends RuntimeException {
    public SectorAlreadyExistentException(Sector foundSector) {
        super(String.format("Sector with name already exists [Id: {}][Name: {}]",
                foundSector.getId(),
                foundSector.getName()));
    }
}
