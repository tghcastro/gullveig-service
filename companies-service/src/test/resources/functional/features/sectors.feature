Feature: Companies Sectors

  Scenario: Create a new sector successfully
    Given an unregistered sector
    When a client tries to register this sector
    Then the sector is correctly created
    And it is enabled to use