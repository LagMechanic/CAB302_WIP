package com.zenbrowser.a1.model.User;

import java.util.List;

/**
 * Interface for the Contact Data Access Object that handles
 * the CRUD operations for the Contact class with the database.
 */
public interface IUserDAO {
    /**
     * Adds a new contact to the database.
     * @param site The contact to add.
     */
    public void addContact(User site);
    /**
     * Updates an existing contact in the database.
     * @param site The contact to update.
     */
    public void updateContact(User site);
    /**
     * Deletes a contact from the database.
     * @param site The contact to delete.
     */
    public void deleteContact(User site);
    /**
     * Retrieves a contact from the database.
     * @param id The id of the contact to retrieve.
     * @return The contact with the given id, or null if not found.
     */
    public User getContact(int id);
    /**
     * Retrieves all contacts from the database.
     * @return A list of all contacts in the database.
     */
    public List<User> getAllContacts();

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
}