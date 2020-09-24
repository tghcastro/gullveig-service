package com.tghcastro.gullveig.companies.service.exceptions;

import com.tghcastro.gullveig.companies.service.models.Company;
import com.tghcastro.gullveig.companies.service.models.Sector;

import java.text.MessageFormat;

public class SectorInUseException extends RuntimeException {
    public SectorInUseException(Company company, Sector sector) {
        super(MessageFormat.format("Sector is being used [Sector: {0}-{1}][Company: {2}-{3}]",
                sector.getId(),
                sector.getName(),
                company.getId(),
                company.getName()));
    }
}
