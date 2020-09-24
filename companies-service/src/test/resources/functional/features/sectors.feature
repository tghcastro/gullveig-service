Feature: Sector registration
  As a Broker admin,
  I want manage company's sector,
  so that my clients could manage his portfolios by sectors

  Scenario: Successful sector creation
    Given an unregistered sector
    When a client tries to register this sector
    Then the sector is correctly created
    And it is enabled to use

  Scenario: Successful sector data editing
    Given a registered sector
    When a client tries to update this sector data
    Then the sector is correctly updated

  Scenario: Successful sector deleting
    Given a registered sector
    When a client tries to delete this sector
    Then this sector was deleted

  Scenario: Unable to delete a sector associated with a company
    Given an existent company associated with any sector
    When a client tries to delete this sector
    Then this sector was not deleted

  Scenario: Unable to create a sector if its name already exists
    Given a registered sector
    When a client tries to register this sector again
    Then this sector was not created