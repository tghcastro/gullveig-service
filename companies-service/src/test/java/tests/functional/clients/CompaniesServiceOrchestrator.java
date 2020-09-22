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
}
