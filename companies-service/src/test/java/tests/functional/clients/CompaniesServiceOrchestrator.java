package tests.functional.clients;

import tests.functional.api.CompaniesServiceClient;
import tests.functional.api.contracts.*;

public class CompaniesServiceOrchestrator {

    private final CompaniesServiceClient companiesServiceClient;

    public CompaniesServiceOrchestrator(CompaniesServiceClient companiesServiceClient) {
        this.companiesServiceClient = companiesServiceClient;
    }

    public PostSectorResponse createSector(PostSectorRequest sectorToCreate) {
        System.out.println("Creating a new sector");
        return this.companiesServiceClient.PostSector(sectorToCreate);
    }

    public PutSectorResponse updateSector(PutSectorRequest sectorToUpdate) {
        System.out.println("Updating a sector. Id: " + sectorToUpdate.getId());
        return this.companiesServiceClient.PutSector(sectorToUpdate);
    }

    public void deleteSector(String id) {
        System.out.println("Deleting sector. Id: " + id);
        this.companiesServiceClient.DeleteSector(id);
    }

    public GetSectorResponse getSectorById(String id) {
        System.out.println("Getting sector by ID. Id: " + id);
        return this.companiesServiceClient.GetSector(id);
    }

    public PostCompanyResponse createCompany(PostCompanyRequest companyToCreate) {
        System.out.println("Creating a new company");
        return this.companiesServiceClient.PostCompany(companyToCreate);
    }

    public PutCompanyResponse updateCompany(PostCompanyResponse companyToUpdate) {
        System.out.println("Updating a company. Id: " + companyToUpdate.getId());
        return this.companiesServiceClient.PutCompany(companyToUpdate);
    }


    public PostCompanyRequest GetCompanyInstanceWithValidData(String sectorId) {
        String someName = "COM " + System.currentTimeMillis();
        PostCompanyRequest company = new PostCompanyRequest();
        company.setName(someName);
        company.setEnabled(true);
        company.setSector(sectorId);
        return company;
    }

    public PostSectorRequest GetSectorInstanceWithValidData() {
        String someName = "SEC " + System.currentTimeMillis();
        return new PostSectorRequest(someName, true);
    }

    public PostSectorResponse createValidSector() {
        return this.createSector(this.GetSectorInstanceWithValidData());
    }

    public PostCompanyResponse createValidCompany(String sectorId) {
        return this.createCompany(this.GetCompanyInstanceWithValidData(sectorId));
    }
}
