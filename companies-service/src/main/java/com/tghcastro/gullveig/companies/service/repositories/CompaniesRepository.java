package com.tghcastro.gullveig.companies.service.repositories;

import com.tghcastro.gullveig.companies.service.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompaniesRepository extends JpaRepository<Company,Long> {
    List<Company> findAllByOrderByNameAsc();
}
