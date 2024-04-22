package com.zenbrowser.a1.model.Authentication;

public interface IAuthentication {

    public void login(String username, String password) throws InvalidCredentials;

    public void signup(String username, String password) throws UserAlreadyExists;

    public void logout();

    public Boolean userLoggedIn();
}