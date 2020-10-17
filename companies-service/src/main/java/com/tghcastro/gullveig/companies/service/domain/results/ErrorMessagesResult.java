package com.tghcastro.gullveig.companies.service.domain.results;

import com.tghcastro.gullveig.companies.service.domain.models.Company;

public final class ErrorMessagesResult {
    public static String DuplicatedCompany(Company company, Company alreadyExistentCompany) {
        String message = "A company with the same name already exists. [Trying to store: %s][Already existent: %s(%s)]";
        return String.format(message, company.getName(),
                alreadyExistentCompany.getName(),
                alreadyExistentCompany.getId());
    }
}
