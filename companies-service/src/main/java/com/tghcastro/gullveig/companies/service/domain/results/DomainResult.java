package com.tghcastro.gullveig.companies.service.domain.results;

import com.tghcastro.gullveig.companies.service.domain.DomainAction;
import com.tghcastro.gullveig.companies.service.domain.DomainActionParameterized;

public class DomainResult<T> {
    private final T value;
    private final boolean success;
    private final String errorMessage;

    public DomainResult(T value, boolean success, String errorMessage) {
        this.value = value;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public DomainResult(T value) {
        this.value = value;
        this.success = true;
        this.errorMessage = null;
    }

//    public static DomainResult<T> Ok(T value) {
//        return new DomainResult<T>(value, true, null);
//    }
//
//    public static DomainResult<T> error(Object value, String errorMessage) {
//        return new DomainResult<T>(value, false, errorMessage);
//    }

    public boolean failed() {
        return !success;
    }

    public boolean succeeded() {
        return success;
    }

    public DomainResult<T> onSuccess(DomainAction<T> action) {
        if (this.succeeded()) {
            return action.execute();
        }
        return this;
    }

    public DomainResult<T> onSuccess(DomainActionParameterized<T> action) {
        if (this.succeeded()) {
            return action.execute(this);
        }
        return this;
    }

    public DomainResult<T> onFailure(DomainAction<T> action) {
        if (this.failed()) {
            return action.execute();
        }
        return this;
    }

    public T onSuccessReturnValue() {
        if (this.succeeded()) {
            return value;
        }
        return null;
    }

    public String error() {
        return errorMessage;
    }

    public T value() {
        return value;
    }
}
