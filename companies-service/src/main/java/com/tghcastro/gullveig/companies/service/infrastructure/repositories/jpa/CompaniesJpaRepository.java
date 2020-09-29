package com.tghcastro.gullveig.companies.service.infrastructure.repositories.jpa;

import com.tghcastro.gullveig.companies.service.domain.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompaniesJpaRepository extends JpaRepository<Company, Long>, com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.CompaniesRepository {
    List<Company> findAllByOrderByNameAsc();

    Optional<Company> findBySectorId(Long sectorId);
}
