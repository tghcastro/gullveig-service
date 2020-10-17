package com.tghcastro.gullveig.companies.service.domain.exceptions;

public class DuplicatedCompanyNameException extends DomainException {
    public DuplicatedCompanyNameException() {
        super("A company with the same name already exists",
                DomainErrorCode.DUPLICATED_COMPANY_NAME);
    }
}
