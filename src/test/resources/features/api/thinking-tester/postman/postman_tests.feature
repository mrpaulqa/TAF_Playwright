Feature: Run postman collections

  @Postman
  Scenario: Run postman collection for thinking tester with env
    When I run "postman" collection "src/test/resources/Collections/Postman/ThinkingTester/jsonplaceholder_collection(TAF).postman_collection.json" with env "src/test/resources/Collections/Postman/ThinkingTester/Test_env.postman_environment.json"

  @Postman
  Scenario: Run postman collection for thinking tester
    When I run "postman" collection "src/test/resources/Collections/Postman/ThinkingTester/jsonplaceholder_collection(TAF).postman_collection.json"