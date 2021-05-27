@FlightBooking
Feature: Flight Booking

  @ValidScenario
  Scenario: Booking a Flight
    Given Navigate to Page Blazedemo
    When I select departure destination
    And I select arrival destination
    And I click on Find Flights button
    And I choose the Airlines
    When I enter all the required information
    And I enter payment details
    And I capture the confirmation details

  @InvalidScenario
  Scenario: Check for invalid payment details
    Given Navigate to Page Blazedemo
    When I select departure destination
    And I select arrival destination
    And I click on Find Flights button
    And I choose the Airlines
    When I enter all the required information
    When I enter invalid card number
    And I capture the error Message