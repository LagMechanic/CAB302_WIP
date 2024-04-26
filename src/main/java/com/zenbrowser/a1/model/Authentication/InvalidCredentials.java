package com.zenbrowser.a1.model.Authentication;

/**
 * Error to be used when a user logs in with invalid username/password
 */
public class InvalidCredentials extends Exception
{
    public InvalidCredentials() {}
    public InvalidCredentials(String message)
    {
        super(message);
    }
}
