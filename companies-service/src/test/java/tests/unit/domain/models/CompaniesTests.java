package tests.unit.domain.models;

import com.tghcastro.gullveig.companies.service.domain.exceptions.DomainException;
import com.tghcastro.gullveig.companies.service.domain.models.Company;
import org.junit.Test;
import tests.unit.domain.UnitTestDataHelper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompaniesTests {
    @Test
    public void validate_ShouldNotThrowError_WithValidData() {
        Company company = UnitTestDataHelper.companyWithValidData();
        assertDoesNotThrow(company::validate);
    }

    @Test
    public void validate_ShouldThrowError_WithEmptyName() {
        Company company = UnitTestDataHelper.companyWithValidData();
        company.setName("");
        assertThrows(DomainException.class, company::validate);
    }

    @Test
    public void validate_ShouldThrowError_WithNullName() {
        Company company = UnitTestDataHelper.companyWithValidData();
        company.setName(null);
        assertThrows(DomainException.class, company::validate);
    }

    @Test
    public void validate_ShouldThrowError_WithNullSector() {
        Company company = UnitTestDataHelper.companyWithValidData();
        company.setSector(null);
        assertThrows(DomainException.class, company::validate);
    }
}
