package com.zenbrowser.a1.model.Authentication;

/**
 * Error to be used when a user logs in with invalid username/password
 */
class InvalidCredentials extends Exception
{
    public InvalidCredentials() {}
    public InvalidCredentials(String message)
    {
        super(message);
    }
}

/**
 * Error to be used when a user signs up with an email that is already in use
 */
class UserAlreadyExists extends Exception
{
    public UserAlreadyExists() {}
    public UserAlreadyExists(String message)
    {
        super(message);
    }
}

