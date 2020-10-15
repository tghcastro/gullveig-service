package com.tghcastro.gullveig.companies.service.infrastructure.repositories.jpa;

import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.SectorsRepository;
import com.tghcastro.gullveig.companies.service.domain.models.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectorsJpaRepository extends JpaRepository<Sector, Long>, SectorsRepository {
    List<Sector> findAllByOrderByNameAsc();

    Optional<Sector> getByName(String name);
}
