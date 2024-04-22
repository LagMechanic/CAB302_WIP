package com.zenbrowser.a1.model.Authentication;

import java.sql.Connection;

public class SQLiteUsersDAO implements IUsersDAO {

    private Connection connection;

    public  SQLiteUsersDAO(Connection connection) {
        this.connection = connection;
    }

    void CreateTable() {
        // SQL to create users table, unless we use a SQL script

    }


    public void AddUser(String username, String password) {
        //SQL to insert user
    }

}