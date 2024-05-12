package com.zenbrowser.a1.model.ProfileLimits;

import java.sql.*;

public class ProfileLimitsDBManager {
    private static final String DB_URL = "jdbc:sqlite:zenbrowser.db";

    public ProfileLimitsDBManager() {
        createTableIfNotExists();
    }

    public void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS url_limits ("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "url TEXT,"
                + "`limit` TEXT"
                + ")";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUrlAndLimit(String url, String limit) {
        String query = "INSERT INTO url_limits (url, 'limit') VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, url);
            preparedStatement.setString(2, limit);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
