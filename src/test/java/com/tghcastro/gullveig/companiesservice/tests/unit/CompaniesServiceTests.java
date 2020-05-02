package com.tghcastro.gullveig.companiesservice.tests.unit;

import autofixture.publicinterface.Any;
import com.tghcastro.gullveig.companiesservice.exceptions.CompanyNotFoundException;
import com.tghcastro.gullveig.companiesservice.exceptions.SectorNotFoundException;
import com.tghcastro.gullveig.companiesservice.models.Company;
import com.tghcastro.gullveig.companiesservice.models.Sector;
import com.tghcastro.gullveig.companiesservice.repositories.CompaniesRepository;
import com.tghcastro.gullveig.companiesservice.services.CompaniesService;
import com.tghcastro.gullveig.companiesservice.services.CompaniesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CompaniesServiceTests {

    private CompaniesRepository companiesRepository;

    @Autowired
    private CompaniesService companiesService;

    @BeforeEach
    void initUseCase() {
        companiesRepository = Mockito.mock(CompaniesRepository.class);
        companiesService = new CompaniesServiceImpl(companiesRepository);
    }

    @Test
    public void test_getAll_shouldReturnAllCompaniesAndItsSectors() {
        List<Company> fakeCompanies = Any.listOf(Company.class);
        when(companiesRepository.findAllByOrderByNameAsc()).thenReturn(fakeCompanies);

        List<Company> foundCompanies = companiesService.getAll();

        assertThat(foundCompanies).isNotNull().isNotEmpty();
        assertThat(foundCompanies.size()).isEqualTo(fakeCompanies.size());
        foundCompanies.forEach(c -> {
            assertThat(c.getSector()).isNotNull();
        });
        assertThat(foundCompanies.size()).isEqualTo(fakeCompanies.size());
    }

    @Test
    public void test_getAll_shouldReturnEmpty_whenNoCompaniesFound() {
        List<Company> fakeCompanies = new ArrayList<>();
        when(companiesRepository.findAllByOrderByNameAsc()).thenReturn(fakeCompanies);

        List<Company> foundSectors = companiesService.getAll();

        assertThat(foundSectors).isNotNull().isEmpty();
    }

    @Test
    public void test_getById_shouldReturnCompanyWithSector() {
        Company fakeCompany = Any.of(Company.class);
        when(companiesRepository.findById(fakeCompany.getId())).thenReturn(Optional.of(fakeCompany));

        Company foundCompany = companiesService.getById(fakeCompany.getId()).get();

        assertThat(foundCompany).isNotNull();
        assertThat(foundCompany).isEqualTo(fakeCompany);
        assertThat(foundCompany.getSector()).isEqualTo(fakeCompany.getSector());
    }

    @Test
    public void test_getById_shouldThrowException_whenNoCompanyFound() {
        long nonExistentCompany = 1;
        when(companiesRepository.findById(nonExistentCompany)).thenReturn(Optional.ofNullable(null));
        assertThrows(CompanyNotFoundException.class, () -> companiesService.getById(nonExistentCompany));
    }

    @Test
    public void test_create_shouldReturnCreatedCompany() {
        Company fakeCompany = Any.of(Company.class);
        when(companiesRepository.saveAndFlush(fakeCompany)).thenReturn(fakeCompany);

        Company createdCompany = companiesService.create(fakeCompany);

        assertThat(createdCompany).isEqualTo(fakeCompany);
    }

    @Test
    public void test_update_shouldReturnUpdatedCompany() {
        Company originalCompany = new Company("SomeCompany");
        originalCompany.setId((long) 1);
        originalCompany.setEnabled(true);

        Company companyToUpdate = new Company("SomeCompany");
        BeanUtils.copyProperties(originalCompany, companyToUpdate);
        companyToUpdate.setName("New name");

        when(companiesRepository.findById(originalCompany.getId())).thenReturn(Optional.of(originalCompany));
        when(companiesRepository.saveAndFlush(originalCompany)).thenReturn(originalCompany);

        Company updatedCompany = companiesService.update(companyToUpdate.getId(), companyToUpdate);

        assertThat(updatedCompany).isEqualTo(companyToUpdate);
    }

    @Test
    public void test_update_shouldThrowException_whenCompanyDoesNotExist() {
        Company nonExistentCompany = Any.of(Company.class);

        when(companiesRepository.findById(nonExistentCompany.getId())).thenReturn(Optional.ofNullable(null));

        assertThrows(CompanyNotFoundException.class, () -> companiesService.update(nonExistentCompany.getId(), nonExistentCompany));
    }

    @Test
    public void test_delete_shouldUpdateEnableToFalse_whenDeletingCompany() {
        Company originalCompany = new Company("SomeCompany");
        originalCompany.setId((long)1);
        originalCompany.setEnabled(true);

        Company deletedCompany = new Company("SomeCompany");
        BeanUtils.copyProperties(originalCompany, deletedCompany);
        deletedCompany.setEnabled(false);

        when(companiesRepository.findById(originalCompany.getId())).thenReturn(Optional.of(originalCompany));
        when(companiesRepository.saveAndFlush(deletedCompany)).thenReturn(deletedCompany);

        companiesService.delete(deletedCompany.getId());

        verify(companiesRepository, times(1)).saveAndFlush(deletedCompany);
    }

    @Test
    public void test_delete_shouldThrowException_whenCompanyDoesNotExist() {
        Company nonExistentCompany = Any.of(Company.class);

        when(companiesRepository.findById(nonExistentCompany.getId())).thenThrow(SectorNotFoundException.class);

        assertThrows(SectorNotFoundException.class, () -> companiesService.delete(nonExistentCompany.getId()));
    }
}
