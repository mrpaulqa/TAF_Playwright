@ui
Feature: UI smoke test (Playwright)

  Scenario: Playwright home page loads with the expected title
    Given I open the UI home page
    Then the page title should contain "Playwright"
