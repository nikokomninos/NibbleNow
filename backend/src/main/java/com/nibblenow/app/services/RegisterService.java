package com.nibblenow.app.services;

import com.nibblenow.app.Database;
import com.nibblenow.app.User;
import com.nibblenow.app.Cart;

import java.util.ArrayList;

/**
 * RegisterService:
 * 
 * This service is in charge of handling register requests from the user.
 * 
 * @author Katarina Liedbeck
 */
public class RegisterService 
{
  /**
   * @param username - this is a string between 5 and 25 characters.
   * @param password - this is a string between 5 and 25 characters.
   * @param role - this is a string either (Customer, Restaurant Owner, OR Delivery Driver)
   * @return - a User object if all fields entered meet the requirements of creating an account
   */
  public User register(String username, String password, String role) 
  {
    //Checking if any fields are empty.
    if(username.equals("") || password.equals("") || role.equals(""))
    {
      return null;
    }

    //Checking if the username is unique.
    for(User user : Database.USERS)
    {
      if (user.getUsername().equals(username))
      {
        return null;
      }
    }

    //Varible to return.
    User returnedUser = new User(username, password, role, new Cart());
    
    //Checking for valid character lengths in both username and password.
    boolean usernameValidLength = checkLength(username,5,25);
    boolean passwordValidLength = checkLength(password,5,25);
    boolean verifiedLength = false;
    if(usernameValidLength == true && passwordValidLength == true)
    {
      verifiedLength = true;
    }

    //Checking for no blank spaces in username and password.
    boolean usernameNoBlanks = !username.isBlank();
    boolean passwordNoBlanks = !password.isBlank();
    boolean verifiedBlanks = false;
    if(usernameNoBlanks == true && passwordNoBlanks == true)
    {
      verifiedBlanks = true;
    }

    //Checking for no special characters in username and password.
    boolean usernameNoSpecial = checkSpecialChar(username);
    boolean passwordNoSpecial = checkSpecialChar(password);
    boolean verifiedNoSpecial = false;
    if(usernameNoSpecial == true && passwordNoSpecial == true)
    {
      verifiedNoSpecial = true;
    }

    //Checking if the password has an upper case.
    boolean passwordContainsUpperCase = checkUpperCase(password, 1);

    //If all conditions are true
    if(verifiedLength == true && verifiedBlanks == true && verifiedNoSpecial == true && passwordContainsUpperCase == true)
    {
      //Add user to the database and return the user
      Database.USERS.add(returnedUser);
      return returnedUser;
    }
    else
    {
      return null;
    }

  }

  /*
   * checkLength: Method used to check if the length of password or username is valid.
   * @param1: String input (either the username or the password)
   * @param2: Int minlength, should be 5 for both the username or password.
   * @param3: Int maxlength, should be 25 for both username or password.
   * @returns: True if valid length, false otherwise.
   */
  public boolean checkLength(String input, int minLength, int maxLength)
  {
    boolean verifiedLength = false;

    //If input is inbetween a certain number of characters.
    if(input.length()>=minLength && input.length()<=maxLength)
    {
      verifiedLength = true;
    }

    return verifiedLength;
  }

  /*
   * checkSpecialChar: Method used to check if the username or password contains a special character.
   * @param: String input (either the username or the password)
   * @returns: True if there are no special characters, false otherwise.
   */
  public boolean checkSpecialChar(String input)
  {
    for(int i = 0; i < input.length(); i++)
    {
      //If it is a special character
      if(!Character.isLetterOrDigit(input.charAt(i)))
      {
        return false;
      }
    }
    return true;
  }

  /*
   * checkUpperCase: Method used to check if the password has a certain number of uppercase characters.
   * @param1: String input (password)
   * @param2: Int minUpper, should be 1
   * @returns: True if password contains atleast 1 uppercase, false otherwise.
   */
  public boolean checkUpperCase(String input, int minUpper)
  {
    int upperCaseCount = 0;

    //looping through all characters in string.
    for(int i = 0; i < input.length(); i++)
    {
      //Checking if the character is an uppercase
      if(Character.isUpperCase(input.charAt(i)))
      {
        upperCaseCount++;
      }
    }

    //If there is atleast the amount of upper case letters in the string required
    if(upperCaseCount>=minUpper)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
