@api
Feature: API smoke test (REST Assured)

  Scenario: Fetch a single post by id
    When I send a GET request to "/posts/1"
    Then the response status code should be 200
    And the response field "id" should equal 1
