package com.tghcastro.gullveig.companies.service.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyNotFoundBySectorException extends RuntimeException {
    public CompanyNotFoundBySectorException(Long sectorId) {
        super(MessageFormat.format("Company not found [id:{0}]", sectorId));
    }
}
