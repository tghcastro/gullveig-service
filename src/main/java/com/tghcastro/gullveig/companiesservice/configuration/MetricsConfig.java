package com.tghcastro.gullveig.companiesservice.configuration;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.NamingConvention;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class MetricsConfig {

    @Value("${spring.profiles.active}")
    private String springProfile;

    // TODO: Understand how Naming Convention works
    NamingConvention namingConvention = new NamingConvention() {
        @Override
        public String name(String s, Meter.Type type, String s1) {
            return "gullveig";
        }
    };

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() throws UnknownHostException {
        String hostName = InetAddress.getLocalHost().getHostName();
        return registry -> registry
                .config()
                .commonTags("host", hostName)
                .commonTags("env", springProfile)
                .commonTags("service", "companies-service");
    }
}
