package com.bit.db;

import com.bit.utils.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Base class for JDBC database tests. Lazily opens a single connection using
 * the {@code db.*} configuration (MySQL via Docker, or embedded H2 by default)
 * and exposes small query helpers. The connection is shared across step and
 * hook classes within a scenario via PicoContainer dependency injection.
 */
public class BaseDB {

    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(ConfigReader.get("db.driver"));
                connection = DriverManager.getConnection(
                        ConfigReader.get("db.url"),
                        ConfigReader.get("db.user"),
                        ConfigReader.get("db.password"));
            } catch (ClassNotFoundException | SQLException e) {
                throw new IllegalStateException("Could not open DB connection", e);
            }
        }
        return connection;
    }

    public void execute(String sql) {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to execute: " + sql, e);
        }
    }

    public void update(String sql, Object... params) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            bind(ps, params);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to update: " + sql, e);
        }
    }

    public int count(String sql, Object... params) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            bind(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to query: " + sql, e);
        }
    }

    private void bind(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
                // best-effort close
            } finally {
                connection = null;
            }
        }
    }
}
