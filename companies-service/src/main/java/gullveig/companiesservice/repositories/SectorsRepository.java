package gullveig.companiesservice.repositories;

import gullveig.companiesservice.models.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectorsRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAllByOrderByNameAsc();

    Optional<Sector> getByName(String name);
}
