package com.tghcastro.gullveig.gullveigcompaniesservice.repositories;

import com.tghcastro.gullveig.gullveigcompaniesservice.models.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorsRepository extends JpaRepository<Sector, Long> {
    boolean existsCompaniesSectorByName(String name);
}
