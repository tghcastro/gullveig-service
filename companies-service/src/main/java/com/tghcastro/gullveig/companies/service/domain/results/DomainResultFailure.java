package com.tghcastro.gullveig.companies.service.domain.results;

public class DomainResultFailure extends DomainResult {
    public DomainResultFailure(Object value, String errorMessage) {
        super(value, false, errorMessage);
    }
}
