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
    public void addContact(User user);
    /**
     * Updates an existing contact in the database.
     * @param user The contact to update.
     */
    public void updateContact(User user);
    /**
     * Deletes a contact from the database.
     * @param user The contact to delete.
     */
    public void deleteContact(User user);
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
}