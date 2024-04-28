package com.zenbrowser.a1.model.Website;

import com.zenbrowser.a1.model.User.User;

import java.util.List;

public interface ISiteDAO {
    /**
     * Adds a new contact to the database.
     * @param site The contact to add.
     */
    public void addSite(User site);
    /**
     * Updates an existing contact in the database.
     * @param site The contact to update.
     */
    public void updateSite(User site);
    /**
     * Deletes a contact from the database.
     * @param site The contact to delete.
     */
    public void deleteSite(User site);
    /**
     * Retrieves a contact from the database.
     * @param id The id of the contact to retrieve.
     * @return The contact with the given id, or null if not found.
     */
    public void getContact(int id);
    /**
     * Retrieves all contacts from the database.
     * @return A list of all contacts in the database.
     */
    public List<User> getAllContacts();
}

