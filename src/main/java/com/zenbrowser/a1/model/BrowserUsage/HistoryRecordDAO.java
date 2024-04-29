package com.zenbrowser.a1.model.BrowserUsage;

import com.zenbrowser.a1.model.SqliteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class HistoryRecordDAO implements IHistoryRecordDAO {

    private int historyRecordId;

    private Connection connection;

    public HistoryRecordDAO() throws SQLException {
        this.connection = SqliteConnection.getInstance();
    }
    public HistoryRecordDAO(Connection connection) {
        this.connection = connection;
    }

    // method to insert browsing data to database, should call this when user opens website
    // takes id, username, the activity , and the moment the user started using it.
    /**
     * Inserts site into database.
     * @param username username
     * @param site site foreign key string
     * @param historyRecordDateTime date and time user opens site
     */
    public void insertHistoryRecord(String username, String site, String historyRecordDateTime) {
        String sql = "INSERT INTO user_records (    username, site, historyRecordDateTime ) VALUES (?, ?, ?)";

        try(
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,site);
            preparedStatement.setString(3,historyRecordDateTime);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("inserted record successfully");
                    try(var generatedKeys = preparedStatement.getGeneratedKeys()){
                        if (generatedKeys.next()){
                            historyRecordId = generatedKeys.getInt(1);
                            System.out.println("generated key historyRecordId: " + historyRecordId);
                        }
                    }
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
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
        String sql = "UPDATE user_records SET historyRecordEndDateTime = ? WHERE historyRecordID = ?";

        try(
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.setString(1, historyRecordEndDateTime);
            preparedStatement.setInt(2, historyRecordId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<HistoryRecord> getAllUserHistoryRecords(String username) {
        ObservableList<HistoryRecord> historyRecordObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user_records WHERE username = ?";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    HistoryRecord historyRecord = new HistoryRecord(
                            resultSet.getInt("historyRecordID"),
                            resultSet.getString("username"),
                            resultSet.getString("site"),
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
