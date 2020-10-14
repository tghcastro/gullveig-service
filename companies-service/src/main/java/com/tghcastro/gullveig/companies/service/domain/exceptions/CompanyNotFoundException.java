package com.tghcastro.gullveig.companies.service.domain.exceptions;

//TODO: Put this in the correct place
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends DomainException {
    public CompanyNotFoundException(Long id) {
        super(String.format("Company not found [id:%s]", id), DomainErrorCode.COMPANY_NOT_FOUND);
    }

}
