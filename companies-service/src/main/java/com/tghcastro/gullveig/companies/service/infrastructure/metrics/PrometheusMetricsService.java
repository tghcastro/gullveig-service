package com.tghcastro.gullveig.companies.service.infrastructure.metrics;

import com.tghcastro.gullveig.companies.service.domain.interfaces.metrics.MetricsService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class PrometheusMetricsService implements MetricsService {

    private final MeterRegistry meterRegistry;
    private Counter sectorsCreatedCounter;
    private Counter sectorsUpdatedCounter;
    private Counter sectorsDeletedCounter;
    private Counter companiesCreatedCounter;
    private Counter companiesUpdatedCounter;
    private Counter companiesDeletedCounter;

    public PrometheusMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.initializeMetrics();
    }

    private void initializeMetrics() {
        sectorsCreatedCounter = this.meterRegistry.counter("sectors.created");
        sectorsUpdatedCounter = this.meterRegistry.counter("sectors.updated");
        sectorsDeletedCounter = this.meterRegistry.counter("sectors.deleted");
        companiesCreatedCounter = this.meterRegistry.counter("companies.created");
        companiesUpdatedCounter = this.meterRegistry.counter("companies.updated");
        companiesDeletedCounter = this.meterRegistry.counter("companies.deleted");
    }

    @Override
    public void registerSectorCreated() {
        this.sectorsCreatedCounter.increment();
    }

    @Override
    public void registerSectorDeleted() {
        this.sectorsDeletedCounter.increment();
    }

    @Override
    public void registerSectorUpdated() {
        this.sectorsUpdatedCounter.increment();
    }

    @Override
    public void registerCompanyCreated() {
        this.companiesCreatedCounter.increment();
    }

    @Override
    public void registerCompanyUpdated() {
        this.companiesUpdatedCounter.increment();
    }
}
