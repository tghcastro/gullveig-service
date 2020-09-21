package tests.functional.clients;

import tests.functional.api.CompaniesServiceClient;
import tests.functional.api.dtos.SectorRequest;
import tests.functional.api.dtos.SectorResponse;

public class CompaniesServiceOrchestrator {

    private final CompaniesServiceClient companiesServiceClient;

    public CompaniesServiceOrchestrator(CompaniesServiceClient companiesServiceClient) {
        this.companiesServiceClient = companiesServiceClient;
    }

    public SectorResponse createSector(SectorRequest sectorToCreate) {
        System.out.println("Creating a new sector");

        return this.companiesServiceClient.PostSector(sectorToCreate);
    }
}
