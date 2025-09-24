package com.nibblenow.app.services;

import com.nibblenow.app.User;
import com.nibblenow.app.Database;
import com.nibblenow.app.User;

/**
 * LoginService:
 * 
 * This service is in charge of handling log in requests from the user.
 * 
 * @author Christopher Sciortino
 */
public class LoginService
{
  /**
   * @param username - this is a string between 5 and 25 characters.
   * @param password - this is a string between 5 and 25 characters.
   * @return - a User object if it exists in the database and they entered the correct
   *           password for that user.
   */
  public User login(String username, String password)
  {
    if(username.length() > 25 || username.length() < 5 || password.length() < 5 || password.length() > 25)
    {
      return(null);
    }

    for(User user : Database.USERS)
    {
      if (user.getUsername().equals(username) && user.getPassword().equals(password))
      {
        return(user);
      }
    }
    return(null);
  }
}