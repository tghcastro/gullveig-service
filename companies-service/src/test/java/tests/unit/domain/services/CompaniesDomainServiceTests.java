package tests.unit.domain.services;

import com.tghcastro.gullveig.companies.service.domain.exceptions.DomainException;
import com.tghcastro.gullveig.companies.service.domain.exceptions.DuplicatedCompanyNameException;
import com.tghcastro.gullveig.companies.service.domain.interfaces.metrics.MetricsService;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.CompaniesRepository;
import com.tghcastro.gullveig.companies.service.domain.models.Company;
import com.tghcastro.gullveig.companies.service.domain.services.CompaniesDomainService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tests.unit.domain.UnitTestDataHelper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CompaniesDomainServiceTests {

    private MetricsService metricsService;
    private CompaniesRepository companiesRepository;
    private CompaniesDomainService companiesDomainService;

    @Before
    public void beforeTest() {
        metricsService = mock(MetricsService.class);
        companiesRepository = mock(CompaniesRepository.class);
        companiesDomainService = new CompaniesDomainService(companiesRepository, metricsService);
        Mockito.clearInvocations();
    }

    @Test
    public void create_ShouldCreate_WhenNoCampanyWithSameNameExists() {
        Company company = UnitTestDataHelper.companyWithValidData();

        when(companiesRepository.saveAndFlush(any(Company.class))).thenReturn(company);

        assertDoesNotThrow(() -> companiesDomainService.create(company));

        verify(companiesRepository, times(1)).saveAndFlush(company);
        verify(metricsService, times(1)).registerCompanyCreated();
    }

    @Test
    public void create_ShouldThrowError_WhenAlreadyExistsCompanyWithSame() {
        Company alreadyExistentCompany = UnitTestDataHelper.companyWithValidData();
        Company companyToCreate = UnitTestDataHelper.companyWithValidData();

        when(companiesRepository.findByName(alreadyExistentCompany.getName())).thenReturn(alreadyExistentCompany);

        assertThrows(DuplicatedCompanyNameException.class, () -> companiesDomainService.create(companyToCreate));

        verify(companiesRepository, only()).findByName(alreadyExistentCompany.getName());
        verify(companiesRepository, times(0)).saveAndFlush(any(Company.class));
        verify(metricsService, times(0)).registerCompanyCreated();
    }

    @Test
    public void update_ShouldUpdate_WhenUpdatingACompanyThatExists() {
        Company companyToUpdate = UnitTestDataHelper.companyWithValidData();

        when(companiesRepository.findById(companyToUpdate.getId())).thenReturn(Optional.of(companyToUpdate));
        when(companiesRepository.saveAndFlush(companyToUpdate)).thenReturn(companyToUpdate);

        assertDoesNotThrow(() -> companiesDomainService.update(
                companyToUpdate.getId(),
                companyToUpdate));

        verify(companiesRepository, times(1)).findById(companyToUpdate.getId());
        verify(companiesRepository, times(1)).saveAndFlush(companyToUpdate);
        verify(metricsService, only()).registerCompanyUpdated();
    }

    @Test
    public void update_ShouldThrowError_WhenUpdatingACompanyThatDoesNotExits() {
        Company companyToUpdate = UnitTestDataHelper.companyWithValidData();

        when(companiesRepository.findById(companyToUpdate.getId())).thenReturn(Optional.empty());

        assertThrows(DomainException.class,
                () -> companiesDomainService.update(
                        companyToUpdate.getId(),
                        companyToUpdate));

        verify(companiesRepository, only()).findById(companyToUpdate.getId());
        verify(metricsService, never()).registerCompanyUpdated();
    }

    @Test
    public void update_ShouldThrowError_WhenUpdatingACompanyNameToOneAlreadyExistent() {
        Company alreadyExistentCompany = UnitTestDataHelper.companyWithValidData(100);
        Company companyToUpdate = UnitTestDataHelper.companyWithValidData(200);
        companyToUpdate.setName(alreadyExistentCompany.getName());

        when(companiesRepository.findByName(companyToUpdate.getName())).thenReturn(alreadyExistentCompany);
        when(companiesRepository.findById(companyToUpdate.getId())).thenReturn(Optional.of(companyToUpdate));
        CompaniesDomainService companiesDomainService = new CompaniesDomainService(companiesRepository, metricsService);

        assertThrows(DuplicatedCompanyNameException.class,
                () -> companiesDomainService.update(
                        companyToUpdate.getId(),
                        companyToUpdate));

        verify(companiesRepository, times(1)).findById(companyToUpdate.getId());
        verify(companiesRepository, times(1)).findByName(alreadyExistentCompany.getName());
        verify(companiesRepository, never()).saveAndFlush(any(Company.class));
        verify(metricsService, never()).registerCompanyUpdated();
    }

    @Test
    public void update_ShouldThrowError_WhenUpdatingACompanyNameToAnInvalidSector() {
        Company companyToUpdate = UnitTestDataHelper.companyWithValidData();
        companyToUpdate.setSector(null);

        assertThrows(DomainException.class,
                () -> companiesDomainService.update(
                        companyToUpdate.getId(),
                        companyToUpdate));

        verify(companiesRepository, never()).findById(companyToUpdate.getId());
        verify(companiesRepository, never()).findByName(companyToUpdate.getName());
        verify(companiesRepository, never()).saveAndFlush(any(Company.class));
        verify(metricsService, never()).registerCompanyUpdated();
    }
}
