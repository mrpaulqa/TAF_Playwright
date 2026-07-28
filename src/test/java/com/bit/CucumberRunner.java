package com.bit;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * JUnit Platform Suite that discovers and runs all Gherkin feature files under
 * {@code src/test/resources/features} via the Cucumber engine, with the Allure
 * reporting plugin enabled. Executed by the standard {@code gradle test} task.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@IncludeTags("sauceLogin")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.bit.steps,com.bit.hooks")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class CucumberRunner {
}
