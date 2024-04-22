package com.zenbrowser.a1.model.Authentication;

/**
 * System for user signup, login and logout
 */
public class Authentication implements IAuthentication {

    private String username;
    private Boolean userLoggedIn;

    public Authentication() {
        username = null;
        userLoggedIn = false;
    }

    /**
     *
     * @param username user's username
     * @param password user's password
     */
    @Override
    public void login(String username, String password)  {
        //throw new InvalidCredentials();
        this.username = username;
        userLoggedIn = true;
    }

    /**
     *
     * @param username user's username
     * @param password user's password
     */
    @Override
    public void signup(String username, String password) {
        //throw new UserAlreadyExists();
        this.username = username;
        userLoggedIn = true;
    }

    /**
     *
     */
    @Override
    public void logout() {
        this.username = null;
        userLoggedIn = false;
    }

    /**
     * @return true if a user is currently logged in
     */
    @Override
    public Boolean userLoggedIn() {
        return userLoggedIn;
    }

}
