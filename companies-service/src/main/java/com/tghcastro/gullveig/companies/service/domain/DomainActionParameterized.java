package com.tghcastro.gullveig.companies.service.domain;

import com.tghcastro.gullveig.companies.service.domain.results.DomainResult;

public interface DomainActionParameterized<T> {
    DomainResult<T> execute(DomainResult<T> lastResult);
}