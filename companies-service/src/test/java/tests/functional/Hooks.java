package tests.functional;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    private ScenarioDataContext scenarioDataContext;

    public Hooks(ScenarioDataContext scenarioDataContext) {
        this.scenarioDataContext = scenarioDataContext;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioDataContext.setScenario(scenario);
        scenarioDataContext.startContext();
    }

    @After
    public void afterScenario(Scenario scenario) {
        scenarioDataContext.finishContext();
    }
}
