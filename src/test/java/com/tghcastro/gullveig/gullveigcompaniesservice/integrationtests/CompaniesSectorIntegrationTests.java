package com.tghcastro.gullveig.gullveigcompaniesservice.integrationtests;

import com.tghcastro.gullveig.gullveigcompaniesservice.models.CompaniesSector;
import com.tghcastro.gullveig.gullveigcompaniesservice.repositories.CompaniesSectorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CompaniesSectorIntegrationTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompaniesSectorRepository repository;

    @Test
    public void whenFindById_thenReturnSector() {
        // given
        CompaniesSector sector = new CompaniesSector("Finance");
        entityManager.persist(sector);
        entityManager.flush();

        // when
        CompaniesSector found = repository.getOne(sector.getId());

        // then
        assertThat(found.getName()).isEqualTo(sector.getName());
    }
}
