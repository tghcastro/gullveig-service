package com.tghcastro.gullveig.companies.service.domain.interfaces.repositories;

import com.tghcastro.gullveig.companies.service.domain.models.Sector;

import java.util.List;
import java.util.Optional;

public interface SectorsRepository {
    List<Sector> findAllByOrderByNameAsc();

    Optional<Sector> getByName(String name);

    Optional<Sector> findById(Long id);

    Sector saveAndFlush(Sector sector);
}
