package com.zenbrowser.a1.model.Authentication;

/**
 * System for user signup, login and logout
 */
public class Authentication implements IAuthentication {

    private String username;
    public String getUsername() {
        return username;
    }


    private Boolean userLoggedIn;
    private final IUsersDAO usersDAO;

    public Authentication(IUsersDAO usersDAO) {
        username = null;
        userLoggedIn = false;
        this.usersDAO = usersDAO;
    }

    /**
     *
     * @param username user's username
     * @param password user's password
     */
    @Override
    public void login(String username, String password) throws InvalidCredentials {
        if (!usersDAO.CheckUsername(username)) throw new InvalidCredentials();
        if (!usersDAO.CheckPassword(username, password)) throw new InvalidCredentials();

        this.username = username;
        userLoggedIn = true;
    }

    /**
     *
     * @param username user's username
     * @param password user's password
     */
    @Override
    public void signup(String username, String password) throws UserAlreadyExists {
        if (usersDAO.CheckUsername(username)) throw new UserAlreadyExists();

        usersDAO.AddUser(username, password);

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
