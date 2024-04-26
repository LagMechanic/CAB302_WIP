package com.zenbrowser.a1.model.BrowserUsage;

import com.zenbrowser.a1.model.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class HistoryRecordDAO implements IHistoryRecordDAO {

    private int historyRecordID;

    // method to insert browsing data to database, should call this when user opens website
    // takes id, username, the activity , and the moment the user started using it.

    /**
     * Inserts site into database.
     * @param username username
     * @param site site foreign key string
     * @param historyRecordDateTime date and time user opens site
     */
    public void insertHistoryRecord(String username, String site, String historyRecordDateTime) {
        String sql = "INSERT INTO user_records (    username, site, historyRecordDateTime ) VALUES (?, ?, ?,)";

        try(Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,site);
            preparedStatement.setString(3,historyRecordDateTime);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("inserted record successfully");
                    try(var generatedKeys = preparedStatement.getGeneratedKeys()){
                        if (generatedKeys.next()){
                            historyRecordID = generatedKeys.getInt(1);
                            System.out.println("generated key RecordID: " + historyRecordID);
                        }
                    }
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // method to update the row with time the activity ended
    // pass activityID which has the generated Pkey from earlier, takes end time as a parameter
    // should be called when user done with current site

    /**
     *  updates row with HistoryRecordEndDateTime where activityID matches this activityID
     * @param HistoryRecordEndDateTime Date/time user finished activity
     */
    public void updateActivityEndDateTime(String HistoryRecordEndDateTime){
        String sql = "UPDATE user_activity SET HistoryRecordEndDateTime = ? WHERE activityID = ?";

        try(Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, historyRecordID);
            preparedStatement.setString(2,HistoryRecordEndDateTime);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<HistoryRecord> getAllUserHistoryRecords(String username) {
        ObservableList<HistoryRecord> historyRecordObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user_activity WHERE username = ?";

        try (Connection connection = DatabaseConnection.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    HistoryRecord historyRecord = new HistoryRecord(
                            resultSet.getInt("historyRecordID"),
                            resultSet.getString("username"),
                            resultSet.getString("historyRecord"),
                            resultSet.getString("historyRecordDateTime"),
                            resultSet.getString("historyRecordEndDateTime")
                    );
                    historyRecordObservableList.add(historyRecord);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historyRecordObservableList;
    }
}
