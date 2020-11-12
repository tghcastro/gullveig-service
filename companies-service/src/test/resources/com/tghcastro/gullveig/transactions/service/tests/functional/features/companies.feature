Feature: Companies

  Scenario: Successful company creation
    Given an existent sector
    And an unregistered company from this sector
    When a client tries to register this company
    Then the company is correctly created
    And it is enabled to use

  Scenario: Successful company data editing
    Given an existent company associated with any sector
    When a client tries to update this company name
    Then the company correctly updated

  Scenario: Successful change company's sector
    Given an existent company associated with any sector
    And another existent sector
    When a client tries to update this company to this another sector
    Then the company correctly updated

  Scenario: Successful stocks addition
    Given an existent company associated with any sector
    When a client tries to register the stock "ABC"
    Then stock is added in this company
    And this company has 1 stock associated