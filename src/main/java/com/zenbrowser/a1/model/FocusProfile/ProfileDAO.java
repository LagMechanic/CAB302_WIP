package com.zenbrowser.a1.model.FocusProfile;

import com.zenbrowser.a1.model.Website.Site;
import com.zenbrowser.a1.model.Website.SiteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAO {
    private Connection connection;
    private int profileId;

    public ProfileDAO(Connection connection) {
        this.connection = connection;
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
                        profileId = generatedKeys.getInt(1);
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
        SiteDAO siteDAO = new SiteDAO(connection);
        Site website = siteDAO.getSiteById(resultSet.getInt("websiteId"));

        Profile profile = new Profile(resultSet.getString("profileName"), website);
        profile.setId(resultSet.getInt("id"));
        return profile;
    }
}
