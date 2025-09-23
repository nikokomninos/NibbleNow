package com.nibblenow.app;

public class User {
  private String username;
  private String password;
  private String role;

  public User(String username, String password, String role)
  {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public String getUsername()
  {
    return this.username;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getRole()
  {
    return this.role;
  }

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

  public String toString()
  {
    return ("Username: " + this.username + "\nPassword: " + this.password + "\nRole: " + this.role);
  }
}