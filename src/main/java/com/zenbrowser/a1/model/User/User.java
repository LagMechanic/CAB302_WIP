package com.zenbrowser.a1.model.User;

public class User {
    private int id;
    private String username;
    private String lastName;

    public User(String username, String lastName, String email, String phone) {
        this.username = username;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return username + " " + lastName;
    }
}