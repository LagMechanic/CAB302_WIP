package com.zenbrowser.a1.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection instance = null;

    private DatabaseConnection() {
        String url = "jdbc:sqlite:database.db";
        try{
            instance = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            new DatabaseConnection();
        }
        return instance;
    }
}
