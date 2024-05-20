package com.zenbrowser.a1.model.BrowserUsage;

import com.zenbrowser.a1.model.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryRecordDAO implements IHistoryRecordDAO {

    private int historyRecordId;

    private Connection connection;

    public HistoryRecordDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS history ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username VARCHAR NOT NULL,"
                    + "URL VARCHAR NOT NULL,"
                    + "siteName VARCHAR NULL,"
                    + "historyRecordDateTime DATETIME NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // method to insert browsing data to database, should call this when user opens website
    // takes id, username, the activity , and the moment the user started using it.
    /**
     * Inserts site into database.
     * @param record history record.
     */
    @Override
    public void insertHistoryRecord(HistoryRecord record) {
        String sql = "INSERT INTO history (username, URL, siteName, historyRecordDateTime ) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, record.getUsername());
            preparedStatement.setString(2, record.getURL());
            preparedStatement.setString(3, record.getSite());
            preparedStatement.setTimestamp(4, record.getHistoryRecordDate());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                historyRecordId = generatedKeys.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // method to update the row with time the activity ended
    // pass recordId which has the generated Pkey from earlier, takes end time as a parameter
    // should be called when user done with current site

    /**
     *  updates row with historyRecordEndDateTime where recordId matches this recordId
     * @param historyRecordEndDateTime Date/time user finished activity
     */
    public void updateActivityEndDateTime(String historyRecordEndDateTime){
        String sql = "UPDATE history SET historyRecordEndDateTime = ? WHERE historyRecordID = ?";

        try(
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.setString(1, historyRecordEndDateTime);
            preparedStatement.setInt(2, historyRecordId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HistoryRecord> getAllUserHistoryRecords(String username) {
        List<HistoryRecord> historyRecords = new ArrayList<>();
        String sql = "SELECT * FROM history WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HistoryRecord historyRecord = new HistoryRecord(
                        resultSet.getString("username"),
                        resultSet.getString("siteName"),
                        resultSet.getString("URL"),
                        resultSet.getTimestamp("historyRecordDateTime")
                );
                historyRecord.setId(resultSet.getInt("id"));
                historyRecords.add(historyRecord);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historyRecords;
    }
}