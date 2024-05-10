package com.zenbrowser.a1.model.Authentication;

import com.zenbrowser.a1.model.User.User;

public interface IAuthentication {

    void login(User user) throws InvalidCredentials, UserAlreadyExists;

    void signup(User user) throws UserAlreadyExists;

    void logout();

    Boolean userLoggedIn();
}