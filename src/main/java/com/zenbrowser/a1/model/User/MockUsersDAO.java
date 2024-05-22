package com.zenbrowser.a1.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.zenbrowser.a1.model.User.IUserDAO;
import com.zenbrowser.a1.model.User.User;

public class MockUsersDAO implements IUserDAO {

    private final Map<String, String> Users;

    public MockUsersDAO() {
        Users = new HashMap<>();
    }

    /**
     * Adds a new user into the database
     *
     */
    @Override
    public void addUser(User user) {
        if (Users.containsKey(user.getUsername())) return;
        Users.put(user.getUsername(), user.getPassword());
    }

    @Override
    public void updateContact(User user) {
        Users.put(user.getUsername(), user.getPassword());
    }


    @Override
    public User getContact(String username) {
        return null;
    }

    /**
     * Checks if there is a user with the given username
     *
     * @param username username to check
     * @return true if username is in database
     */
    @Override
    public boolean checkUsername(String username) {
        return Users.containsKey(username);
    }

    /**
     * Checks if there is a user with the given username and password
     *
     * @param username username to check
     * @param password password to check
     * @return true if a user has the given username and password
     */
    @Override
    public boolean checkPassword(String username, String password) {
        if (!checkUsername(username)) return false;
        return Objects.equals(Users.get(username), password);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
