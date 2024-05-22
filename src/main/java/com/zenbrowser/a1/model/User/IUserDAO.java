package com.zenbrowser.a1.model.User;

import java.util.List;

/**
 * Interface for the Contact Data Access Object that handles
 * the CRUD operations for the Contact class with the database.
 */
public interface IUserDAO {
    /**
     * Adds a new contact to the database.
     * @param user The contact to add.
     */
    public void addUser(User user);
    /**
     * Updates an existing user in the database.
     * @param user The user to update.
     */
    public void updateContact(User user);


    public User getContact(String username);
    /**
     * Checks if there is a user with the given username
     * @param username username to check
     * @return true if username is in database
     */
    public boolean checkUsername(String username);

    /**
     * Checks if there is a user with the given username and password
     * @param username username to check
     * @param password password to check
     * @return true if a user has the given username and password
     */
    public boolean checkPassword(String username, String password);

    public List<User> getAllUsers();
}