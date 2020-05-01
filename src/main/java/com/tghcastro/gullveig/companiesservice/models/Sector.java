package com.tghcastro.gullveig.companiesservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="CompaniesSectors")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
