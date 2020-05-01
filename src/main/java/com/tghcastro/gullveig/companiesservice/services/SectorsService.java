package com.tghcastro.gullveig.companiesservice.services;

import com.tghcastro.gullveig.companiesservice.models.Sector;

import java.util.List;

public interface SectorsService {
    Sector getById(Long id);

    List<Sector> getAll();

    Sector create(Sector sector);

    void delete(Long id);

    Sector update(Long id, Sector sector);
}
