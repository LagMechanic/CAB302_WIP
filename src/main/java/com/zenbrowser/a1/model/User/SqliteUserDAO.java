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
        // Used for testing, to be removed later
        //insertSampleData();
    }

    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM users";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO users (firstName, lastName, phone, email) VALUES "
                    + "('John', 'Doe', '0423423423', 'johndoe@example.com'),"
                    + "('Jane', 'Doe', '0423423424', 'janedoe@example.com'),"
                    + "('Jay', 'Doe', '0423423425', 'jaydoe@example.com')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
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
    @Override
    public void addContact(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, firstName, lastName, phone, email) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2,user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5,user.getPhone());
            statement.setString(6, user.getEmail());
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
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, phone = ?, email = ? WHERE id = ?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getLastName());
            statement.setInt(5, user.getId());
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
    public User getContact(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Integer phone = resultSet.getInt("phone");
                String email = resultSet.getString("email");
                User user = new User( password, firstName, lastName, phone, email);
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<User> getAllContacts() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Integer phone = resultSet.getInt("phone");
                String email = resultSet.getString("email");
                User user = new User(password, firstName, lastName, phone, email);
                user.setId(id);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}