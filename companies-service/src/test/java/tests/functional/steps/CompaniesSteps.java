package tests.functional.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.functional.ScenarioDataContext;
import tests.functional.api.contracts.PostCompanyRequest;
import tests.functional.api.contracts.PostCompanyResponse;
import tests.functional.api.contracts.PostSectorResponse;
import tests.functional.clients.CompaniesServiceOrchestrator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CompaniesSteps {
    private final ScenarioDataContext scenarioDataContext;
    private final CompaniesServiceOrchestrator companiesServiceOrchestrator;

    public CompaniesSteps(ScenarioDataContext scenarioDataContext, CompaniesServiceOrchestrator companiesServiceOrchestrator) {
        this.scenarioDataContext = scenarioDataContext;
        this.companiesServiceOrchestrator = companiesServiceOrchestrator;
    }

    @Given("an existent sector")
    public void anExistentSector() {
        PostSectorResponse sector = companiesServiceOrchestrator.createValidSector();
        scenarioDataContext.put("createdSector", sector);
    }

    @And("an unregistered company from this sector")
    public void anUnregisteredCompany() {
        PostSectorResponse createdSector = scenarioDataContext.get("createdSector");
        PostCompanyRequest postCompanyRequest = companiesServiceOrchestrator.GetCompanyInstanceWithValidData(createdSector.getId());
        scenarioDataContext.put("companyToCreate", postCompanyRequest);
    }

    @When("a client tries to register this company")
    public void aClientTriesToRegisterThisCompany() {
        PostCompanyRequest companyToCreate = scenarioDataContext.get("companyToCreate");
        PostCompanyResponse response = companiesServiceOrchestrator.createCompany(companyToCreate);
        scenarioDataContext.put("createdCompany", response);

    }

    @Then("the company is correctly created")
    public void theCompanyIsCorrectlyCreated() {
        PostCompanyRequest companyToCreate = scenarioDataContext.get("companyToCreate");
        PostCompanyResponse createdCompany = scenarioDataContext.get("createdCompany");
        assertThat(createdCompany.getName(), is(companyToCreate.getName()));
        assertThat(createdCompany.getSector().getId(), is(companyToCreate.getSector().getId()));
        assertThat(createdCompany.getId(), is(notNullValue()));
    }
}
