package com.nibblenow.app;

/**
 * User:
 * 
 * An object that represents a User
 * 
 * @author Nikolaos Komninos, Christopher Sciortino
 */
public class User {
  private String username;
  private String password;
  private String role;

  /**
   * Constructor for the User class
   * @param username a User's username
   * @param password a User's password
   * @param role a User's role
   */
  public User(String username, String password, String role)
  {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  /**
   * Gets a User's username
   * @return a User's username
   */
  public String getUsername()
  {
    return this.username;
  }

  /**
   * Gets a User's password
   * @return a User's password
   */
  public String getPassword()
  {
    return this.password;
  }

  /**
   * Gets a User's role
   * @return a User's role
   */
  public String getRole()
  {
    return this.role;
  }

  /**
   * Custom equals method to check if two
   * users are equal
   * @param user_p the User being tested for equality
   * @return true if equal, false otherwise
   */
  public boolean equals(User user_p)
  {
    if(user_p == null)
    {
      return(false);
    }

    boolean equals = false;
    
    if(user_p.getUsername().equals(this.username))
    {
      if(user_p.getPassword().equals(this.password))
      {
        if(user_p.getRole().equals(this.role))
        {
          equals = true;
        }
      }
    }
    return(equals);
  }

  /**
   * Prints all of a User's information
   */
  public String toString()
  {
    return ("Username: " + this.username + "\nPassword: " + this.password + "\nRole: " + this.role);
  }
}