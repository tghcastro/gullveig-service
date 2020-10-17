package tests.functional.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.functional.CompaniesServiceAdministrator;
import tests.functional.ScenarioDataContext;
import tests.functional.api.contracts.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CompaniesSteps {
    private final ScenarioDataContext scenarioDataContext;
    private final CompaniesServiceAdministrator companiesServiceAdministrator;

    public CompaniesSteps(ScenarioDataContext scenarioDataContext, CompaniesServiceAdministrator companiesServiceAdministrator) {
        this.scenarioDataContext = scenarioDataContext;
        this.companiesServiceAdministrator = companiesServiceAdministrator;
    }

    @Given("an existent company associated with any sector")
    public void anExistentCompanyAssociatedWithAnySector() {
        PostSectorResponse createdSector = companiesServiceAdministrator.createValidSector();
        scenarioDataContext.put("createdSector", createdSector);
        PostCompanyResponse postCompanyResponse = companiesServiceAdministrator.createValidCompany(createdSector.getId());
        scenarioDataContext.put("createdCompany", postCompanyResponse);
    }

    @And("an unregistered company from this sector")
    public void anUnregisteredCompany() {
        PostSectorResponse createdSector = scenarioDataContext.get("createdSector");
        PostCompanyRequest postCompanyRequest = companiesServiceAdministrator.GetCompanyInstanceWithValidData(createdSector.getId());
        scenarioDataContext.put("companyToCreate", postCompanyRequest);
    }

    @When("a client tries to register this company")
    public void aClientTriesToRegisterThisCompany() {
        PostCompanyRequest companyToCreate = scenarioDataContext.get("companyToCreate");
        PostCompanyResponse response = companiesServiceAdministrator.createCompany(companyToCreate);
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

    @When("a client tries to update this company name")
    public void aClientTriesToUpdateThisCompanyName() {
        PostCompanyResponse companyToUpdate = scenarioDataContext.get("createdCompany");
        String newName = "NEW " + System.currentTimeMillis();
        companyToUpdate.setName(newName);
        PutCompanyResponse updatedCompany = companiesServiceAdministrator.updateCompany(companyToUpdate);
        scenarioDataContext.put("companyToUpdate", companyToUpdate);
        scenarioDataContext.put("updatedCompany", updatedCompany);
    }

    @Then("the company correctly updated")
    public void theCompanyCorrectlyUpdated() {
        PostCompanyResponse companyToUpdate = scenarioDataContext.get("companyToUpdate");
        PutCompanyResponse updatedCompany = scenarioDataContext.get("updatedCompany");
        assertThat(updatedCompany.getId(), is(companyToUpdate.getId()));
        assertThat(updatedCompany.getName(), is(companyToUpdate.getName()));
        assertThat(updatedCompany.getSector().getId(), is(companyToUpdate.getSector().getId()));
        assertThat(updatedCompany.getStocks().size(), is(companyToUpdate.getStocks().size()));
    }

    @When("a client tries to update this company to this another sector")
    public void aClientTriesToUpdateThisCompanyToThisAnotherSector() {
        PostCompanyResponse companyToUpdate = scenarioDataContext.get("createdCompany");
        PostSectorResponse anotherCreatedSector = scenarioDataContext.get("anotherCreatedSector");
        PostCompanyResponse.CompanySector sector = new PostCompanyResponse.CompanySector();
        sector.setId(anotherCreatedSector.getId());
        companyToUpdate.setSector(sector);
        PutCompanyResponse updatedCompany = companiesServiceAdministrator.updateCompany(companyToUpdate);
        scenarioDataContext.put("updatedCompany", updatedCompany);
        scenarioDataContext.put("companyToUpdate", companyToUpdate);
    }

    @When("a client tries to register the stock {string}")
    public void aClientTriesToRegisterTheStock(String ticker) {
        PostCompanyResponse companyResponse = scenarioDataContext.get("createdCompany");
        PostCompanyStockResponse postCompanyStockResponse = companiesServiceAdministrator.addStock(companyResponse.getId(), ticker);
        scenarioDataContext.put("companyWithStockAdded", postCompanyStockResponse);
        scenarioDataContext.put("stockTickerAdded", ticker);
    }

    @Then("stock is added in this company")
    public void stockIsAddedInThisCompany() {
        PostCompanyStockResponse postCompanyStockResponse = scenarioDataContext.get("companyWithStockAdded");
        String ticker = scenarioDataContext.get("stockTickerAdded");
        assertThat(postCompanyStockResponse.getStocks().size(), is(greaterThan(0)));
        assertThat(postCompanyStockResponse.getStocks().get(0).getTicker(), is(ticker));
    }

    @And("this company has {int} stock associated")
    public void thisCompanyHasStockAssociated(int numberOfStocks) {
        PostCompanyStockResponse postCompanyStockResponse = scenarioDataContext.get("companyWithStockAdded");
        String ticker = scenarioDataContext.get("stockTickerAdded");
        assertThat(postCompanyStockResponse.getStocks().size(), is(numberOfStocks));
        assertThat(postCompanyStockResponse.getStocks().get(0).getTicker(), is(ticker));
    }
}
