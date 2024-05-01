package com.zenbrowser.a1.model.User;

import com.zenbrowser.a1.model.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDAO implements IUserDAO {
    private Connection connection;

    public SqliteUserDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }


    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR PRIMARY KEY,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "email VARCHAR NULL,"
                    + "password VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addContact(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, firstName, lastName, email) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2,user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.executeUpdate();
            // Set the id of the new contact
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContact(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, email = ? WHERE id = ?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteContact(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getContact(String Username) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setString(1, Username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                User user = new User(username, password, firstName, lastName, email);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if there is a user with the given username
     * @param username username to check
     * @return true if username is in database
     */
    @Override
    public boolean checkUsername(String username) {
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
    public boolean checkPassword(String username, String password) {
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