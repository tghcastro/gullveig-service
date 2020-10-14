package com.tghcastro.gullveig.companies.service.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micrometer.core.instrument.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity(name = "CompaniesStocks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 5, message = "Ticker should have between 1 and 5 characters")
    @NotEmpty(message = "Ticker is mandatory")
    private String ticker;

    public Stock() {
    }

    public Stock(String ticker) {
        if (StringUtils.isNotEmpty(ticker)) {
            ticker = ticker.trim().toUpperCase();
        }
        this.ticker = ticker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public String setTicker() {
        return ticker;
    }
}
