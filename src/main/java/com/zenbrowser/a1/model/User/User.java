package com.zenbrowser.a1.model.User;

public class User {

    private Integer id;
    private String userName;

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phone;

    public User(String userName, String password, String firstName, String lastName,  Integer phoneNo, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phoneNo;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.phone = null;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void getPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

}