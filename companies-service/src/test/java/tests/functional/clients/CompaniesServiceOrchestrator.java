package tests.functional.clients;

import tests.functional.api.CompaniesServiceClient;
import tests.functional.api.dtos.PostSectorRequest;
import tests.functional.api.dtos.PostSectorResponse;
import tests.functional.api.dtos.PutSectorRequest;
import tests.functional.api.dtos.PutSectorResponse;

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
        System.out.println("Updating a sector");
        return this.companiesServiceClient.PutSector(sectorToUpdate);
    }
}
