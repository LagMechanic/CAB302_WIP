package com.zenbrowser.a1.model.Authentication;

/**
 * Error to be used when a user signs up with an email that is already in use
 */
public class UserAlreadyExists extends Exception
{
    public UserAlreadyExists() {}
    public UserAlreadyExists(String message)
    {
        super(message);
    }
}

