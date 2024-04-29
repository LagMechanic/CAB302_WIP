package com.zenbrowser.a1.model.Authentication;

public interface IAuthentication {

    void login(String username, String password) throws InvalidCredentials;

    void signup(String username, String password) throws UserAlreadyExists;

    void logout();

    Boolean userLoggedIn();
}