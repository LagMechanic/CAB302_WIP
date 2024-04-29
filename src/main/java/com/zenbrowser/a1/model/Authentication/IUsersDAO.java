package com.zenbrowser.a1.model.Authentication;

public interface IUsersDAO {
    /**
     * Adds a new user into the database
     * @param username User's username
     * @param password User's password
     */
    void AddUser(String username, String password);

    /**
     * Checks if there is a user with the given username
     * @param username username to check
     * @return true if username is in database
     */
    boolean CheckUsername(String username);

    /**
     * Checks if there is a user with the given username and password
     * @param username username to check
     * @param password password to check
     * @return true if a user has the given username and password
     */
    boolean CheckPassword(String username, String password);
}