package tests.functional.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.functional.ScenarioDataContext;
import tests.functional.api.contracts.*;
import tests.functional.clients.CompaniesServiceOrchestrator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SectorsSteps {

    private ScenarioDataContext scenarioDataContext;
    private CompaniesServiceOrchestrator companiesServiceOrchestrator;

    public SectorsSteps(ScenarioDataContext scenarioDataContext, CompaniesServiceOrchestrator companiesServiceOrchestrator) {
        this.scenarioDataContext = scenarioDataContext;
        this.companiesServiceOrchestrator = companiesServiceOrchestrator;
    }

    @Given("^an unregistered sector$")
    public void anUnregisteredSector() {
        PostSectorRequest postSectorRequest = PostSectorRequest.CreateWithValidData();
        scenarioDataContext.put("sector", postSectorRequest);
    }

    @When("^a client tries to register this sector$")
    public void aClientTriesToRegisterThisSector() {
        PostSectorRequest sector = scenarioDataContext.get("sector");
        PostSectorResponse response = companiesServiceOrchestrator.createSector(sector);
        scenarioDataContext.put("createdSector", response);
    }

    @Then("^the sector is correctly created$")
    public void theSectorIsCorrectlyCreated() {
        PostSectorRequest sector = scenarioDataContext.get("sector");
        PostSectorResponse createdSector = scenarioDataContext.get("createdSector");
        assertThat(createdSector.getName(), is(sector.getName()));
        assertThat(createdSector.getId(), is(notNullValue()));
    }

    @And("^it is enabled to use$")
    public void itIsEnabledToUse() {
        PostSectorResponse createdSector = scenarioDataContext.get("createdSector");
        assertTrue(createdSector.isEnabled());
    }

    @Given("an registered sector")
    public void anRegisteredSector() {
        PostSectorRequest postSectorRequest = PostSectorRequest.CreateWithValidData();
        PostSectorResponse createdSector = companiesServiceOrchestrator.createSector(postSectorRequest);
        assertThat(createdSector.getId(), is(notNullValue()));
        scenarioDataContext.put("createdSector", createdSector);
    }

    @When("a client tries to update this sector data")
    public void aClientTriesToUpdateThisSectorData() {
        PostSectorResponse createdSector = scenarioDataContext.get("createdSector");
        PutSectorRequest sectorToUpdate = PutSectorRequest.cloneFrom(createdSector);
        sectorToUpdate.setName("NEW " + System.currentTimeMillis());
        PutSectorResponse updatedSector = companiesServiceOrchestrator.updateSector(sectorToUpdate);
        scenarioDataContext.put("sectorToUpdate", sectorToUpdate);
        scenarioDataContext.put("updatedSector", updatedSector);
    }

    @Then("the sector is correctly updated")
    public void theSectorIsCorrectlyUpdated() {
        PutSectorRequest sectorToUpdate = scenarioDataContext.get("sectorToUpdate");
        PutSectorResponse updatedSector = scenarioDataContext.get("updatedSector");
        assertThat(updatedSector.getId(), is(sectorToUpdate.getId()));
        assertThat(updatedSector.getName(), is(sectorToUpdate.getName()));
    }

    @When("a client tries to delete this sector data")
    public void aClientTriesToDeleteThisSectorData() {
        PostSectorResponse createdSector = scenarioDataContext.get("createdSector");
        companiesServiceOrchestrator.deleteSector(createdSector.getId());
        scenarioDataContext.put("deletedSectorId", createdSector.getId());
    }

    @Then("the sector is correctly deleted")
    public void theSectorIsCorrectlyDeleted() {
        String sectorId = scenarioDataContext.get("deletedSectorId");
        GetSectorResponse sector = companiesServiceOrchestrator.getSectorById(sectorId);
        assertThat(sector.getId(), is(sectorId));
        assertFalse(sector.isEnabled());
    }
}
