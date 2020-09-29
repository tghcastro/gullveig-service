package com.tghcastro.gullveig.companies.service.domain.exceptions;

import com.tghcastro.gullveig.companies.service.domain.models.Company;
import com.tghcastro.gullveig.companies.service.domain.models.Sector;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class SectorInUseException extends RuntimeException {
    public SectorInUseException(Company company, Sector sector) {
        super(MessageFormat.format("Sector is being used [Sector: {0}-{1}][Company: {2}-{3}]",
                sector.getId(),
                sector.getName(),
                company.getId(),
                company.getName()));
    }
}
