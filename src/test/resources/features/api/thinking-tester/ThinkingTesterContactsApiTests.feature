@apiThinkingTester
Feature: API Thinking Tester Contacts Tests

  Scenario: Fetch a single post by id
    When User get all contacts
    Then the response status code should be 200
    Then User get contact by ID "6a74926a0cf3330015396ae3"
    Then the response status code should be 200


  Scenario: User can created new contact
    When User create a new contact with firstName "John", lastName "Doe", birthdate "1990-01-01", email "john.doe@example.com", phone "234567890", street1 "123 Main St", city "Anytown", owner "Jane Doe"
    Then the response status code should be 201
