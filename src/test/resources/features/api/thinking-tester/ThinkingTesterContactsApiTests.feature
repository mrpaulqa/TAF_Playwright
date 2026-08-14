@apiThinkingTester
Feature: API Thinking Tester Contacts Tests

  Scenario: Fetch a single post by id
    When User get all contacts
    Then the response status code should be 200
    Then User get contact by ID "6a74926a0cf3330015396ae3"
    Then the response status code should be 200
