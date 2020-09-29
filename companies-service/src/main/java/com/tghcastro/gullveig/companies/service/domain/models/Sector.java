package com.tghcastro.gullveig.companies.service.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity(name="CompaniesSectors")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 30, message = "Name should have between 5 and 30 characters")
    @NotEmpty(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Enabled is mandatory")
    private boolean enabled;

    public Sector() {
        enabled = true;
    }

    public Sector(String name) {
        this.name = name;
        this.enabled = true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sector sector = (Sector) o;
        return enabled == sector.enabled &&
                Objects.equals(id, sector.id) &&
                Objects.equals(name, sector.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, enabled);
    }
}
