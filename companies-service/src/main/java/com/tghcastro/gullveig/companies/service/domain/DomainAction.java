package com.tghcastro.gullveig.companies.service.domain;

import com.tghcastro.gullveig.companies.service.domain.results.DomainResult;

public interface DomainAction<T> {
    DomainResult<T> execute();
}