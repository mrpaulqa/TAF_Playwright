@SAUCE_UI
Feature: UI tests for Login Page

  @sauceLogin
  Scenario: Test login page
     Given I open SauceDemo login page
     Then the page title should contain "test page"