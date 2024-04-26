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
        String sql = "INSERT INTO profiles (ProfileName, Website_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, profile.getProfileName());
            statement.setInt(2, profile.getWebsite().getId());
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
