package com.zenbrowser.a1.model.Authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MockUsersDAO implements IUsersDAO {

    private final Map<String, String> Users;

    public MockUsersDAO() {
        Users = new HashMap<>();
    }

    /**
     * Adds a new user into the database
     *
     * @param username User's username
     * @param password User's password
     */
    @Override
    public void AddUser(String username, String password) {
        if (Users.containsKey(username)) return;
        Users.put(username, password);
    }

    /**
     * Checks if there is a user with the given username
     *
     * @param username username to check
     * @return true if username is in database
     */
    @Override
    public boolean CheckUsername(String username) {
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
    public boolean CheckPassword(String username, String password) {
        if (!CheckUsername(username)) return false;
        return Objects.equals(Users.get(username), password);
    }
}
