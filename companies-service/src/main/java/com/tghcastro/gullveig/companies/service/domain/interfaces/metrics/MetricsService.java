package com.tghcastro.gullveig.companies.service.domain.interfaces.metrics;

public interface MetricsService {
    void registerSectorCreated();

    void registerSectorDeleted();

    void registerSectorUpdated();

    void registerCompanyCreated();

    void registerCompanyUpdated();
}
