package com.zenbrowser.a1.model.Website;

import com.zenbrowser.a1.model.SqliteConnection;
import com.zenbrowser.a1.model.User.User;

import java.sql.Statement;
import java.sql.*;
import java.util.List;
public class SQliteWebsiteDAO implements ISiteDAO{
    private Connection connection;

    public SQliteWebsiteDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
        // Used for testing, to be removed later
        //insertSampleData();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS sites ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "siteName VARCHAR NOT NULL,"
                    + "URL VARCHAR NOT NULL,"
                    + "category VARCHAR NOT NULL,"
                    + "blocked BOOLEAN NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSite(User site) {

    }

    @Override
    public void updateSite(User site) {

    }

    @Override
    public void deleteSite(User site) {

    }

    @Override
    public void getContact(int id) {

    }

    @Override
    public List<User> getAllContacts() {
        return null;
    }
}
