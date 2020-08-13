package gullveig.companiesservice.repositories;

import gullveig.companiesservice.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompaniesRepository extends JpaRepository<Company,Long> {
    List<Company> findAllByOrderByNameAsc();
}
