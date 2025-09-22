package com.nibblenow.app;

public class User {
  private String username;
  private String password;
  private String role;

  public User(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public String getRole() {
    return this.role;
  }
}