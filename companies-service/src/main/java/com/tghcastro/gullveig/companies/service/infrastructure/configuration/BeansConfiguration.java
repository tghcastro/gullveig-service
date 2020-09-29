package com.tghcastro.gullveig.companies.service.infrastructure.configuration;

import com.tghcastro.gullveig.companies.service.domain.interfaces.metrics.MetricsService;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.CompaniesRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.SectorsRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.CompaniesService;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.SectorsService;
import com.tghcastro.gullveig.companies.service.domain.services.CompaniesDomainService;
import com.tghcastro.gullveig.companies.service.domain.services.SectorsDomainService;
import com.tghcastro.gullveig.companies.service.infrastructure.metrics.PrometheusMetricsService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    MetricsService metricsService(MeterRegistry meterRegistry) {
        return new PrometheusMetricsService(meterRegistry);
    }

    @Bean
    CompaniesService companiesService(CompaniesRepository companiesRepository, MetricsService metricsService) {
        return new CompaniesDomainService(companiesRepository, metricsService);
    }

    @Bean
    SectorsService sectorsService(SectorsRepository sectorsRepository, CompaniesService companiesService, MetricsService metricsService) {
        return new SectorsDomainService(sectorsRepository, companiesService, metricsService);
    }
}
