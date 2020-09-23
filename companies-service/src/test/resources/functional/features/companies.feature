Feature: Companies

  Scenario: Create a new company
    Given an existent sector
    And an unregistered company from this sector
    When a client tries to register this company
    Then the company is correctly created
    And it is enabled to use