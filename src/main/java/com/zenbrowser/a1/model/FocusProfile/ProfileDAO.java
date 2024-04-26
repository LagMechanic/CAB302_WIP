package com.zenbrowser.a1.model.FocusProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileDAO {
    private Connection connection;

    public ProfileDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertProfile(Profile profile) {
        String sql = "INSERT INTO profiles (profileName, websiteId, profileId) VALUES (?, ? ,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, profile.getProfileName());
            statement.setInt(2, profile.getWebsite().getId());
            statement.setInt(3, profile.getId());
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
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

    public Profile getProfileById(int id){
        String sql = "SELECT * FROM profiles WHERE id=?";
    }
}
