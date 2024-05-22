package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import com.zenbrowser.a1.model.SqliteConnection;
import com.zenbrowser.a1.model.Website.Site;
import com.zenbrowser.a1.model.Website.SiteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO implements IProfileDAO {
    private final Connection connection;

    public ProfileDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable(){
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS profiles ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username VARCHAR,"
                    + "profileName VARCHAR NOT NULL,"
                    + "websiteID VARCHAR NOT NULL,"
                    + "blockedUntil DATETIME NOT NULL,"
                    + "FOREIGN KEY (username) REFERENCES users(username)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Profile insertProfile(Profile profile) {
        String sql = "INSERT INTO profiles (username, profileName, websiteId, blockedUntil) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,profile.getProfileUser());
            statement.setString(2, profile.getProfileName());
            statement.setInt(3, profile.getWebsite().getId());
            statement.setDate(4, profile.getBlockedUntil());
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("inserted record successfully");
                try(var generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        int profileId = generatedKeys.getInt(1);
                        profile.setId(profileId);
                        return profile;
                    }
                }
            }
        }

        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return profile;
    }

    public void updateProfile(Profile profile) {
        String sql = "UPDATE profiles SET profileName=?, websiteId=? WHERE id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, profile.getProfileName());
            statement.setInt(2, profile.getWebsite().getId());
            statement.setInt(3, profile.getId());
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteProfile(int id) throws SQLException {
        String sql = "DELETE FROM profiles WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }


    @Override
    public Profile getProfile(int id) {
        String sql = "SELECT * FROM profiles WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return extractProfileFromResultSet(resultSet);
                }
                else return null;
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profile> getUserProfiles(String username) {
        List<Profile> result = new ArrayList<>();
        String sql = "SELECT * FROM profiles WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(extractProfileFromResultSet(resultSet));
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<HistoryRecord> getUserHistoryRecords(String username) {
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

    @Override
    public Profile getProfileByNameAndSite(String profileName, Site site) {
        String sql = "SELECT * FROM profiles WHERE profileName=?, websiteID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, profileName);
            statement.setInt(2, site.getId());
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return extractProfileFromResultSet(resultSet);
                }
                else return null;
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    private Profile extractProfileFromResultSet(ResultSet resultSet) throws SQLException {

        Site website = new SiteDAO().getSiteById(resultSet.getInt("websiteId"));
        Profile profile = new Profile(
                resultSet.getString("username"),
                resultSet.getString("profileName"),
                website,
                resultSet.getDate("blockedUntil"));

        profile.setId(resultSet.getInt("id"));

        return profile;
    }


}