package com.zenbrowser.a1.model.BrowserUsage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDatabaseUtil {
    public static void createUserRecordsTableWithData() {
        try (Connection connection = TestDatabaseConnection.getInstance()) {
            if (connection != null) {
                Statement statement = connection.createStatement();

                // Create user_records table
                String createUserActivityTableSQL = "CREATE TABLE IF NOT EXISTS user_records (" +
                        "historyRecordID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "username TEXT," +
                        "site TEXT," +
                        "historyRecordDateTime TEXT," +
                        "historyRecordEndDateTime TEXT)";
                statement.execute(createUserActivityTableSQL);

                // Insert sample data
                String insertDataSQL = "INSERT INTO user_records (username, site, historyRecordDateTime, historyRecordEndDateTime) " +
                        "VALUES ('testUser', 'Visited example.com', '2024-04-25 10:30:00', '2024-04-25 11:45:00'), " +
                        "('testUser', 'Visited google.com', '2024-04-26 12:00:00', '2024-04-26 13:30:00'), " +
                        "('testUser2', 'Visited yahoo.com', '2024-04-25 09:00:00', '2024-04-25 10:00:00')";
                statement.execute(insertDataSQL);

                System.out.println("Test database setup completed.");
            } else {
                System.out.println("Failed to establish connection to the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static void dropUserRecordsTable(){
        try (Connection connection = TestDatabaseConnection.getInstance();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user_records;");
            // Repeat the above line for each table you want to drop
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
