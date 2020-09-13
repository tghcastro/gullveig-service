package com.tghcastro.gullveig.companies.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Long id) {
        super(MessageFormat.format("Company not found [id:{0}]", id));
    }

}
