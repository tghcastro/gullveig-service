package tests.functional.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.functional.ScenarioDataContext;
import tests.functional.api.dtos.SectorRequest;
import tests.functional.api.dtos.SectorResponse;
import tests.functional.clients.CompaniesServiceOrchestrator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
        SectorRequest sectorRequest = SectorRequest.CreateWithValidData();
        scenarioDataContext.put("sector", sectorRequest);
    }

    @When("^a client tries to register this sector$")
    public void aClientTriesToRegisterThisSector() {
        SectorRequest sector = scenarioDataContext.get("sector");
        SectorResponse response = companiesServiceOrchestrator.createSector(sector);
        scenarioDataContext.put("createdSector", response);
    }

    @Then("^the sector is correctly created$")
    public void theSectorIsCorrectlyCreated() {
        SectorRequest sector = scenarioDataContext.get("sector");
        SectorResponse createdSector = scenarioDataContext.get("createdSector");
        assertThat(createdSector.getName(), is(sector.getName()));
        assertThat(createdSector.getId(), is(notNullValue()));
    }

    @And("^it is enabled to use$")
    public void itIsEnabledToUse() {
        SectorResponse createdSector = scenarioDataContext.get("createdSector");
        assertTrue(createdSector.isEnabled());
    }
}
