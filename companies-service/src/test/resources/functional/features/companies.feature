Feature: Companies

  Scenario: Create a new company
    Given an existent sector
    And an unregistered company from this sector
    When a client tries to register this company
    Then the company is correctly created
    And it is enabled to use

  Scenario: Update company data
    Given an existent company associated to any sector
    When a client tries to update this company name
    Then the company correctly updated

  Scenario: Change company's sector
    Given an existent company associated to any sector
    And another existent sector
    When a client tries to update this company to this another sector
    Then the company correctly updated