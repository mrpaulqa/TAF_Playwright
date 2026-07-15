package com.bit.hooks;

import com.bit.db.BaseDB;
import io.cucumber.java.After;

/**
 * Closes the shared JDBC connection after every DB scenario. The {@link BaseDB}
 * instance is injected by PicoContainer and is the same one used by the steps.
 */
public class DatabaseHooks {

    private final BaseDB db;

    public DatabaseHooks(BaseDB db) {
        this.db = db;
    }

    @After("@db")
    public void closeConnection() {
        db.close();
    }
}
