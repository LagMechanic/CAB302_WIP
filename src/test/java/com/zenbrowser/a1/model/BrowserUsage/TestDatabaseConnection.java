package com.zenbrowser.a1.model.BrowserUsage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseConnection {
    private static Connection instance = null;

    private TestDatabaseConnection() {
        String url = "jdbc:sqlite:test_database.db";
        try{
            instance = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            new TestDatabaseConnection();
        }
        return instance;
    }
}
