package com.tghcastro.gullveig.companies.service.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tghcastro.gullveig.companies.service.domain.exceptions.DomainErrorCode;
import com.tghcastro.gullveig.companies.service.domain.exceptions.DomainException;
import com.tghcastro.gullveig.companies.service.domain.results.DomainResult;
import com.tghcastro.gullveig.companies.service.domain.results.DomainResultSuccess;
import io.micrometer.core.instrument.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "companies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50, message = "Name should have between 5 and 50 characters")
    @NotEmpty(message = "Name is mandatory")
    private String name;

    @NotNull
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "sector_id", referencedColumnName = "id")
    private Sector sector;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private final List<Stock> stocks;

    public Company() {
        this.enabled = true;
        this.stocks = new ArrayList<Stock>();
    }

    public Company(String name) {
        this.enabled = true;
        this.name = name;
        this.stocks = new ArrayList<Stock>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Stock> getStocks() {
        return this.stocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return enabled == company.enabled &&
                Objects.equals(id, company.id) &&
                Objects.equals(name, company.name) &&
                Objects.equals(sector, company.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, enabled, sector);
    }

    public DomainResult validate() {
        if (StringUtils.isEmpty(this.name)) {
            return DomainResult.error(this, "Company's name should not be empty");
        }
        if (this.sector == null) {
            return DomainResult.error(this, "Company's sector should not be null");
        }
        // TODO: Should I validate name already existent here?

        return new DomainResultSuccess(this);
    }

    public void addStock(Stock stock) {
        if (StringUtils.isEmpty(stock.getTicker())) {
            throw new DomainException("Company's ticker should not be empty", DomainErrorCode.INVALID_COMPANY_DATA);
        }
        this.stocks.add(stock);
        // TODO: Should I validate if the ticker already exists?
    }

    public void addStock(String ticker) {
        this.addStock(new Stock(ticker));
    }
}
