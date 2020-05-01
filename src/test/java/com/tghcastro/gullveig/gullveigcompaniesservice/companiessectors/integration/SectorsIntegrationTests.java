package com.tghcastro.gullveig.gullveigcompaniesservice.companiessectors.integration;

import com.tghcastro.gullveig.gullveigcompaniesservice.models.Sector;
import com.tghcastro.gullveig.gullveigcompaniesservice.repositories.SectorsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SectorsIntegrationTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SectorsRepository repository;

    @Test
    public void whenFindById_thenReturnSector() {
        // given
        Sector sector = new Sector("Finance");
        entityManager.persist(sector);
        entityManager.flush();

        // when
        Sector found = repository.getOne(sector.getId());

        // then
        assertThat(found.getName()).isEqualTo(sector.getName());
    }
}
