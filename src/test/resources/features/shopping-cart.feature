Feature: Test the shopping cart feature of general store
  Scenario: As a normal user I can add products to the shopping cart
    Given I have launched the mobile application for general store
    When I fill out the info form
    And I add products to the shopping cart and click shopping cart icon
    Then I should see the correct total price
    And I should be able to view the terms
    When I click the visit website button
    Then I can search in the search bar