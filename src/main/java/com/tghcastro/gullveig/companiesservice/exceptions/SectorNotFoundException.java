package com.tghcastro.gullveig.companiesservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SectorNotFoundException extends RuntimeException {
    public SectorNotFoundException(Long id) {
        super("Sector not found [id:" + id + "]");
    }
}
