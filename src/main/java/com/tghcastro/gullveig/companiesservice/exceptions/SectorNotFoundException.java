package com.tghcastro.gullveig.companiesservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Company Sector not found")
public class SectorNotFoundException extends RuntimeException {
    public SectorNotFoundException(Long id) {
        super("Company Sector not found [id:" + id + "]");
    }
}
