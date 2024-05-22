package com.zenbrowser.a1.model.FocusProfile;

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
                    + "profileName VARCHAR NOT NULL,"
                    + "websiteID VARCHAR NOT NULL,"
                    + "blockedUntil DATETIME NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Profile insertProfile(Profile profile) {
        String sql = "INSERT INTO profiles (profileName, websiteId, blockedUntil) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, profile.getProfileName());
            statement.setInt(2, profile.getWebsite().getId());
            statement.setDate(3, profile.getBlockedUntil());
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("inserted record successfully");
                try(var generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        int profileId = generatedKeys.getInt(1);
                        System.out.println("generated key siteId: " + profileId);
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
    public List<Profile> getAllProfiles() {
        String sql = "SELECT * FROM profiles";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            try(ResultSet resultSet = statement.executeQuery()) {
                List<Profile> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(extractProfileFromResultSet(resultSet));
                }

                if (!result.isEmpty()) {
                    return result;

                } else return new ArrayList<>();
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profile getProfileByNameAndSite(String profileName, Site site) {
        System.out.println("getProfileByNameAndSite: profileName:" + profileName + ", url: " + site.getURL() +", siteID: " + site.getId());
        String sql = "SELECT * FROM profiles WHERE profileName=? AND websiteID=?";
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
        SiteDAO siteDAO = new SiteDAO();
        Site website = siteDAO.getSiteById(resultSet.getInt("websiteId"));

        Profile profile = new Profile(resultSet.getString("profileName"), website, resultSet.getDate("blockedUntil"));
        profile.setId(resultSet.getInt("id"));
        return profile;
    }


}