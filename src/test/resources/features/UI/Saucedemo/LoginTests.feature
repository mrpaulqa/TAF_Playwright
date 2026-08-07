@ui @SAUCE_UI
Feature: UI tests for Login Page

  @sauceLogin
  Scenario: Test login page
     Given I open SauceDemo login page
     Then the page title should contain "Swag Labs"
     When user login as "standard_user"
     Then I check that page title is "Products"
     When I add backpack to shopping card
     Then verify that items count in the shopping card equals 1