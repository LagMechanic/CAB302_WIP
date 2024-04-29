package com.zenbrowser.a1.model.Authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersDAO implements IUsersDAO {

    private final Connection connection;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new user into the database
     * @param username User's username
     * @param password User's password
     */
    @Override
    public void AddUser(String username, String password) {
        try {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if there is a user with the given username
     * @param username username to check
     * @return true if username is in database
     */
    @Override
    public boolean CheckUsername(String username) {
        try {
            // Select 1 if username is in table
            String sql = "SELECT 1 FROM users WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            // Return true iff any rows exist
            return resultSet.first();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if there is a user with the given username and password
     * @param username username to check
     * @param password password to check
     * @return true if a user has the given username and password
     */
    @Override
    public boolean CheckPassword(String username, String password) {
        try {
            // Select 1 if username is in table
            String sql = "SELECT 1 FROM users WHERE username=?, password=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            // Return true iff any rows exist
            return resultSet.first();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}