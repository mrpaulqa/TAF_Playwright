package com.bit.hooks;

import com.bit.db.BaseDB;
import io.cucumber.java.After;

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
