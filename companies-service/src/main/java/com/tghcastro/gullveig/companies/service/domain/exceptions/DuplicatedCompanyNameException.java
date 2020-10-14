package com.tghcastro.gullveig.companies.service.domain.exceptions;

import com.tghcastro.gullveig.companies.service.domain.models.Company;

public class DuplicatedCompanyNameException extends DomainException {
    public DuplicatedCompanyNameException(Company alreadyExistentCompany, Company companyToStore) {
        super(String.format(
                "A company with the same name already exists. [Trying to store: %s][Already existent: %s(%s)]",
                companyToStore.getName(),
                alreadyExistentCompany.getName(),
                alreadyExistentCompany.getId()),
                DomainErrorCode.DUPLICATED_COMPANY_NAME);
    }
}
