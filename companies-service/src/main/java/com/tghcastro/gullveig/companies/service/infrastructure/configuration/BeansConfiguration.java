package com.tghcastro.gullveig.companies.service.infrastructure.configuration;

import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.CompaniesRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.SectorsRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.CompaniesService;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.SectorsService;
import com.tghcastro.gullveig.companies.service.domain.services.CompaniesDomainService;
import com.tghcastro.gullveig.companies.service.domain.services.SectorsDomainService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    CompaniesService companiesService(CompaniesRepository companiesRepository) {
        return new CompaniesDomainService(companiesRepository);
    }

    @Bean
    SectorsService sectorsService(SectorsRepository sectorsRepository, CompaniesService companiesService, MeterRegistry meterRegistry) {
        return new SectorsDomainService(sectorsRepository, companiesService, meterRegistry);
    }
}
