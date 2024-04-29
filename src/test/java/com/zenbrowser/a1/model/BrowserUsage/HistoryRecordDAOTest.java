package com.zenbrowser.a1.model.BrowserUsage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class HistoryRecordDAOTest {


    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;
    @BeforeEach
    public void setUp() throws SQLException {

        // Create a mock DatabaseConnection
        mockConnection = mock(Connection.class);

        // Create a mock PreparedStatement
        mockStatement = mock(PreparedStatement.class);

        mockResultSet = mock(ResultSet.class);

    }
    @Test
    void insertHistoryRecord_noAffectedRows() throws SQLException {
        when(mockConnection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(0);

        HistoryRecordDAO dao = new HistoryRecordDAO(mockConnection);
        String username = "testUser";
        String site = "example.com";
        String dateTime = "2024-04-25 10:30:00";

        dao.insertHistoryRecord(username, site, dateTime);

        // verify generatedKeys.getInt(1); was not  called
        verify(mockStatement, never()).getGeneratedKeys();

        // Verify that getInt(1) method was called on mockResultSet
        verify(mockResultSet, never()).getInt(1);

    }

    @Test
    void insertHistoryRecord_withAffectedRows() throws SQLException {
        when(mockConnection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        when(mockStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(anyInt())).thenReturn(1);

        HistoryRecordDAO dao = new HistoryRecordDAO(mockConnection);
        String username = "abcUser";
        String site = "example.com";
        String dateTime = "2024-04-25 10:30:00";

        dao.insertHistoryRecord(username, site, dateTime);

        // verify generatedKeys.getInt(1); was called
        verify(mockStatement).getGeneratedKeys();

        // Verify that getInt(1) method was called on mockResultSet
        verify(mockResultSet).getInt(1);
     }

    @Test
    void updateActivityEndDateTime() throws SQLException{
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        HistoryRecordDAO dao = new HistoryRecordDAO(mockConnection);
        String endTime = "2024-04-25 11:45:00";

        dao.updateActivityEndDateTime(endTime);


    }

    @Test
    void getAllUserHistoryRecords() throws SQLException{
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        HistoryRecordDAO dao = new HistoryRecordDAO(mockConnection);

    }
}