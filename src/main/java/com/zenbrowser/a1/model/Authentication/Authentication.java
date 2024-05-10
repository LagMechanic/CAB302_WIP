package com.zenbrowser.a1.model.Authentication;

import com.zenbrowser.a1.model.User.IUserDAO;
import com.zenbrowser.a1.model.User.MockUsersDAO;
import com.zenbrowser.a1.model.User.SqliteUserDAO;
import com.zenbrowser.a1.model.User.User;

/**
 * System for user signup, login and logout, stores current user
 * Usage: Authentication.getInstance().login/signup/logout
 */
public class Authentication implements IAuthentication {

    private User currUser;

    private final IUserDAO userDAO;

    private static Authentication instance;

    /**
     *
     * @return The instance of Authentication
     */
    public static Authentication getInstance() {
        if (instance == null) instance = new Authentication(new SqliteUserDAO());
        return instance;
    }

    /**
     * Used for testing only, will break usage of getInstance()
     * @return New testing instance of Authentication
     */
    public static Authentication getTestInstance() {
        instance = new Authentication(new MockUsersDAO());
        return instance;
    }

    private Authentication(IUserDAO userDAO) {
        currUser = null;
        this.userDAO = userDAO;
    }


    @Override
    public void login(User user) throws InvalidCredentials, MissingUser {
        if (!userDAO.checkUsername(user.getUsername())) throw new MissingUser();
        if (!userDAO.checkPassword(user.getUsername(), user.getPassword())) throw new InvalidCredentials();

        currUser = user;
    }

    @Override
    public void signup(User user) throws UserAlreadyExists {
        if (userDAO.checkUsername(user.getUsername())) throw new UserAlreadyExists();

        userDAO.addContact(user);

        currUser = user;
    }

    /**
     *
     */
    @Override
    public void logout() {
        currUser = null;
    }

    /**
     * @return true if a user is currently logged in
     */
    @Override
    public Boolean userLoggedIn() {
        return currUser != null;
    }

    public class MissingUser extends Throwable {
    }
}

