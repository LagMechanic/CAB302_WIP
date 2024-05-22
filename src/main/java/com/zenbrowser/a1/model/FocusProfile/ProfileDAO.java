package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.SqliteConnection;

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
                    + "siteURL VARCHAR NOT NULL,"
                    + "siteName VARCHAR NULL,"
                    + "category VARCHAR NULL,"
                    + "blockedUntil DATETIME NOT NULL,"
                    + "blockTime TIME NOT NULL,"
                    + "FOREIGN KEY (username) REFERENCES users(username)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Profile insertProfile(Profile profile) {
        String sql = "INSERT INTO profiles (username, profileName, siteURL, blockedUntil, blockTime) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,profile.getProfileUser());
            statement.setString(2, profile.getProfileName());
            statement.setString(3, profile.getSiteURL());
            statement.setTime(4, profile.getBlockedUntil());
            statement.setTime(5, profile.getBlockTime());
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0){
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
        String sql = "UPDATE profiles SET profileName=?, siteURL=? WHERE id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, profile.getProfileName());
            statement.setString(2, profile.getSiteURL());
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
        String sql = "SELECT * FROM profiles WHERE username=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(extractProfileFromResultSet(resultSet));
//                Profile profile = new Profile(
//                        resultSet.getString("username"),
//                        resultSet.getString("profileName"),
//                        resultSet.getString("siteURL"),
//                        resultSet.getTime("blockTime")
//                );
//                profile.setId(resultSet.getInt("id"));
//                result.add(profile);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
    @Override
    public Profile getProfileByNameAndSite(String profileName) {
        String sql = "SELECT * FROM profiles WHERE profileName=?, siteURL=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, profileName);
            statement.setInt(2, );
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
    }**/



    private Profile extractProfileFromResultSet(ResultSet resultSet) throws SQLException {
        Profile profile = new Profile(
                resultSet.getString("username"),
                resultSet.getString("profileName"),
                resultSet.getString("siteURL"),
                resultSet.getTime("blockTime")
        );


        profile.setId(resultSet.getInt("id"));
        return profile;
    }


}