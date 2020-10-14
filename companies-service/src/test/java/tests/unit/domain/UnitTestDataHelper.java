package tests.unit.domain;

import com.tghcastro.gullveig.companies.service.domain.models.Company;
import com.tghcastro.gullveig.companies.service.domain.models.Sector;

public class UnitTestDataHelper {
    public static Company companyWithValidData(int id) {
        Company company = new Company();
        company.setId((long) id);
        company.setName("COCA COLA CO.");
        company.setSector(sectorWithValidData());
        company.setEnabled(true);
        return company;
    }

    public static Company companyWithValidData() {
        return companyWithValidData(1);
    }

    private static Sector sectorWithValidData() {
        Sector sector = new Sector();
        sector.setId(1L);
        sector.setName("Consumer Staples");
        return sector;
    }
}
