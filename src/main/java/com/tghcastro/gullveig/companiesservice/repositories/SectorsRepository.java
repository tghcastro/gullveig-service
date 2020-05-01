package com.tghcastro.gullveig.companiesservice.repositories;

import com.tghcastro.gullveig.companiesservice.models.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorsRepository extends JpaRepository<Sector, Long> {
    boolean existsCompaniesSectorByName(String name);
}
