package tests.functional;

import cucumber.api.Scenario;

import java.util.HashMap;
import java.util.UUID;

public class ScenarioDataContext {

    private Scenario scenario;

    private String requestId;
    private HashMap<String, Object> dataBucket;

    public ScenarioDataContext() {
        this.dataBucket = new HashMap<>();
    }

    public void startContext() {
        System.out.println("Starting Scenario:{} " + scenario.getName() + " = = = = = = = = = = = = = = = =");

        if (requestId == null || requestId == "") {
            requestId = UUID.randomUUID().toString();
        }
    }

    public void finishContext() {
        System.out.println("Scenario Test Data -------------------");
        System.out.println("RequestId: " + requestId);
        System.out.println("Finished Scenario: " + scenario.getName() + " = = = = = = = = = = = = = = = =");

    }

    public <T> void put(String key, T data) {
        dataBucket.put(key, data);
    }

    public <T> T get(String key) {
        return (T) dataBucket.get(key);
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
}
