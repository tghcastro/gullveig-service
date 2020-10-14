package com.tghcastro.gullveig.companies.service.domain.exceptions;

public class DomainException extends RuntimeException {
    private final DomainErrorCode domainErrorCode;

    public DomainException(final String message, DomainErrorCode domainErrorCode) {
        super(message);
        this.domainErrorCode = domainErrorCode;
    }
}
