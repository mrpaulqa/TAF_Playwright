@db
Feature: DB smoke test (JDBC / MySQL)

  Scenario: Insert a user and read it back
    Given a clean "users" table exists
    When I insert a user named "Alice"
    Then the "users" table should contain 1 row
    And a user named "Alice" should exist
