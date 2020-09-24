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

  Scenario: Delete an existent sector successfully
    Given an registered sector
    When a client tries to delete this sector
    Then this sector was deleted

  Scenario: Does not delete sector associated with a company
    Given an existent company associated to any sector
    When a client tries to delete this sector
    Then this sector was not deleted
