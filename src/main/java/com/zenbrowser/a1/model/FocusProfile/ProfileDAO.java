package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.SqliteConnection;
import com.zenbrowser.a1.model.Website.Site;
import com.zenbrowser.a1.model.Website.SiteDAO;

import java.sql.*;

public class ProfileDAO {
    private final Connection connection;

    public ProfileDAO(Connection connection) {
        this.connection = connection;
        createTable();
    }

    private void createTable(){
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS profiles ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "phone VARCHAR NULL,"
                    + "email VARCHAR NULL,"
                    + "password VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Profile insertProfile(Profile profile) {
        String sql = "INSERT INTO profiles (profileName, websiteId) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, profile.getProfileName());
            statement.setInt(2, profile.getWebsite().getId());
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
    public void deleteProfile(int id) throws SQLException {
        String sql = "DELETE FROM profiles WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Profile getProfileById(int id) {
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
    private Profile extractProfileFromResultSet(ResultSet resultSet) throws SQLException {
        SiteDAO siteDAO = new SiteDAO(SqliteConnection.getInstance());
        Site website = siteDAO.getSiteById(resultSet.getInt("websiteId"));

        Profile profile = new Profile(resultSet.getString("profileName"), website);
        profile.setId(resultSet.getInt("id"));
        return profile;
    }
}
