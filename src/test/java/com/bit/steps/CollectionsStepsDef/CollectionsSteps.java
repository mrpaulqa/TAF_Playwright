package com.bit.steps.CollectionsStepsDef;

import com.bit.utils.AuthManager;
import io.cucumber.java.en.When;

import static com.bit.utils.CollectionsRunners.RunnerFactory.getRunner;

public class CollectionsSteps {

    @When("I run {string} collection {string}")
    public void runCollection(String collectionType,String collectionPath) {
        String token = AuthManager.getToken();
        getRunner(collectionType).runWithToken(collectionPath,token);
    }

    @When("I run {string} collection {string} with env {string}")
    public void runCollectionWithEnv(String collectionType,String collectionPath,String env) {
        String token = AuthManager.getToken();
        getRunner(collectionType).run(collectionPath,env,token);
    }

}
