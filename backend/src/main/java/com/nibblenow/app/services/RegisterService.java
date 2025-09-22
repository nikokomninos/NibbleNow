package com.nibblenow.app.services;

import com.nibblenow.app.Database;
import com.nibblenow.app.User;

public class RegisterService {
  public User register(String username, String password, String role) {

    //Variable to check that there is atleast one upper case in the password. 
    int upperCaseCount = 0;

    //Checking username is correct length, and has no spaces.
    if(username.length()>=5 && username.length()<=25 && username.isBlank()==false)
    {
      //Checking if there are any special characters.
      for(int i = 0; i < username.length(); i++)
      {
        if(!Character.isLetterOrDigit(username.charAt(i)))
        {
          return null;
        }
      }
      //Checking if the password is correct length and has no spaces.
      if(password.length()>=5 && password.length()<=25 && password.isBlank()==false)
      {
        //Checking if there are any special characters in password.
        for(int i = 0; i < password.length(); i++)
        {
          if(!Character.isLetterOrDigit(password.charAt(i)))
          {
            return null;
          }
        }
        //Checking if there is atleast one uppercase in password.
        for(int i = 0; i < password.length(); i++)
        {
          if(Character.isUpperCase(password.charAt(i)))
          {
            upperCaseCount++;
          }
        }
        //If there are no upper case characters in password
        if(upperCaseCount==0)
        {
          return null;
        }
      }
    }
    else
    {
      return null;
    }

    User returnedUser = new User(username, password, role);
    return returnedUser;
  }
}