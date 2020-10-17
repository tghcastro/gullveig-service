package com.tghcastro.gullveig.companies.service.domain.results;

import com.tghcastro.gullveig.companies.service.domain.DomainAction;

public class DomainResult {
    private final Object value;
    private final boolean success;
    private final String errorMessage;

    public DomainResult(Object value, boolean success, String errorMessage) {
        this.value = value;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public static DomainResult Ok(Object value) {
        return new DomainResult(value, true, null);
    }

    public static DomainResult error(Object value, String errorMessage) {
        return new DomainResult(value, false, errorMessage);
    }

    public boolean failed() {
        return !success;
    }

    public boolean succeeded() {
        return success;
    }

    public DomainResult onSuccess(DomainAction action) {
        return action.execute();
    }

    public DomainResult onFailure(DomainAction action) {
        return action.execute();
    }

    public Object onSuccessReturnValue() {
        if (this.succeeded()) {
            return value;
        }
        return null;
    }
}
