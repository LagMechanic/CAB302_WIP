package com.zenbrowser.a1.model.BrowserUsage;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class HistoryRecordDAOTest {

    private static final String TEST_DB_URL = "jdbc:sqlite:test_database.db";
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        // Establish connection to the test database before each test
        connection = DriverManager.getConnection(TEST_DB_URL);
    }

    @Test
    void insertHistoryRecord() {
    }

    @Test
    void updateActivityEndDateTime() {
    }

    @Test
    void getAllUserHistoryRecords() {
    }
}