@api
Feature: API smoke test (REST Assured)

  Scenario: Fetch a single post by id
    When I send a GET request to "/posts/1"
    Then the response status code should be 200
    And the response field "id" should equal 1

  Scenario: Verify that post method working correctly
    Then I send a POST request to "/posts" with title "Test 2", body "Some random body" and userId 90
    Then the response status code should be 201
    Then the response body should match the sent post

  Scenario: Verify that post method working correctly with parameters
    Then I send a POST request to "/posts" with title "Test 2", body "Some random body" and userId 90
    Then the response status code should be 201
    Then the response body should match the title "Test 2", body "Some random body" and userId 90

 Scenario: Validate hardcoded value
   Then I send a POST request to "/posts"
   And the response field "title" should equal "Test"
   And the response field "body" should equal "Some random body"
   And the response field "userId" should equal "9"

 Scenario: Verify that post method working correctly with parameters
    Then I send a POST request to "/posts" with title "Test 3", body "Some interesting article" and userId 18
    Then the response status code should be 201
    Then the response body should match the title "Test 3", body "Some interesting article" and userId 18
