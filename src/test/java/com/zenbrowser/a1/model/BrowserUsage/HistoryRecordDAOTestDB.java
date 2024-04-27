package com.zenbrowser.a1.model.BrowserUsage;

import com.zenbrowser.a1.model.DatabaseConnection;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HistoryRecordDAOTestDB {

    @BeforeAll
    static void setupTests(){
        setupTestDatabase();
    }

    @AfterAll
    static void tearDown() throws SQLException {
        TestDatabaseUtil.dropUserRecordsTable();
    }

    public static void setupTestDatabase() {
        TestDatabaseUtil.createUserRecordsTableWithData();
    }
    @Test
     void testInsertHistoryRecord() throws SQLException {
        HistoryRecordDAO dao = new HistoryRecordDAO(TestDatabaseConnection.getInstance());
        String username = "abcUser";
        String site = "example.com";
        String dateTime = "2024-04-25 10:30:00";

        dao.insertHistoryRecord(username, site, dateTime);

        ObservableList<HistoryRecord> allUserHistoryRecords = dao.getAllUserHistoryRecords(username);
        Assertions.assertEquals(1,allUserHistoryRecords.size());
    }



}

