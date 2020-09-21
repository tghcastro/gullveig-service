Feature: Companies Sectors

  Scenario: Create a new sector successfully
    Given an unregistered sector
    When a client tries to register this sector
    Then the sector is correctly created
    And it is enabled to use

  Scenario: Update an existent sector successfully
    Given an registered sector
    When a client tries to update this sector data
    Then the sector is correctly updated