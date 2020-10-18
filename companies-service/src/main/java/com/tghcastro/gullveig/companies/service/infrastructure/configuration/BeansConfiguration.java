package com.tghcastro.gullveig.companies.service.infrastructure.configuration;

import com.tghcastro.gullveig.companies.service.domain.interfaces.metrics.MetricsService;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.CompaniesRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.SectorsRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.StocksRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.CompaniesService;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.SectorsService;
import com.tghcastro.gullveig.companies.service.domain.models.Company;
import com.tghcastro.gullveig.companies.service.domain.services.CompaniesDomainService;
import com.tghcastro.gullveig.companies.service.domain.services.SectorsDomainService;
import com.tghcastro.gullveig.companies.service.infrastructure.metrics.PrometheusMetricsService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    // NOT NEEDED - ONLY HERE AS EXAMPLE

    @Bean
    MetricsService metricsService(MeterRegistry meterRegistry) {
        return new PrometheusMetricsService(meterRegistry);
    }

    @Bean
    CompaniesService<Company> companiesService(CompaniesRepository companiesRepository, StocksRepository stocksRepository, MetricsService metricsService) {
        return new CompaniesDomainService(companiesRepository, stocksRepository, metricsService);
    }

    @Bean
    SectorsService sectorsService(SectorsRepository sectorsRepository, CompaniesService<Company> companiesService, MetricsService metricsService) {
        return new SectorsDomainService(sectorsRepository, companiesService, metricsService);
    }
}
