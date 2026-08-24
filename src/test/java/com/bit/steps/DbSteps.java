package com.bit.steps;

import com.bit.db.BaseDB;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbSteps {

    private final BaseDB db;
    public DbSteps(BaseDB db) {
        this.db = db;
    }

    @Given("a clean {string} table exists")
    public void aCleanTableExists(String table) {
        db.execute("DROP TABLE IF EXISTS " + table);
        db.execute("CREATE TABLE " + table
                + " (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL)");
    }

    @When("I insert a user named {string}")
    public void iInsertAUserNamed(String name) {
        db.update("INSERT INTO users (name) VALUES (?)", name);
    }

    @Then("the {string} table should contain {int} row")
    public void theTableShouldContainRows(String table, int expected) {
        assertEquals(expected, db.count("SELECT COUNT(*) FROM " + table));
    }

    @And("a user named {string} should exist")
    public void aUserNamedShouldExist(String name) {
        assertEquals(1, db.count("SELECT COUNT(*) FROM users WHERE name = ?", name));
    }
}
