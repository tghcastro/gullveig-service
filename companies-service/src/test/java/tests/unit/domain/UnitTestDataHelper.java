package tests.unit.domain;

import com.tghcastro.gullveig.companies.service.domain.models.Company;
import com.tghcastro.gullveig.companies.service.domain.models.Sector;
import com.tghcastro.gullveig.companies.service.domain.models.Stock;

public class UnitTestDataHelper {
    public static Company companyWithValidDataWithoutStocks(int id) {
        Company company = new Company();
        company.setId((long) id);
        company.setName("COCA COLA CO.");
        company.setSector(sectorWithValidData());
        company.setEnabled(true);
        return company;
    }

    public static Company companyWithValidDataWithoutStocks() {
        return companyWithValidDataWithoutStocks(1);
    }

    public static Company companyWithValidDataWithStocks() {
        Company company = companyWithValidDataWithoutStocks();
        company.addStock(stockWithValidData());
        return company;
    }

    private static Sector sectorWithValidData() {
        Sector sector = new Sector();
        sector.setId(1L);
        sector.setName("Consumer Staples");
        return sector;
    }

    public static Stock stockWithValidData() {
        Stock stock = new Stock("KO");
        return stock;
    }
}
