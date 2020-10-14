package com.tghcastro.gullveig.companies.service.domain.exceptions;

public enum DomainErrorCode {
    DUPLICATED_COMPANY_NAME("10001"),
    COMPANY_NOT_FOUND("10002"),
    INVALID_COMPANY_DATA("10003");

    private final String code;

    DomainErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
