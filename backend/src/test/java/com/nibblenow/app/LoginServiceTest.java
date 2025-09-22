package com.nibblenow.app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nibblenow.app.services.LoginService;

/*
 * LoginServiceTest:
 * 
 * This service is in charge of testing the login service is working correctly.
 * 
 * @author Christopher Sciortino
 */
class LoginServiceTest
{

  private LoginService service;
  private boolean result;
  private Database db;

  @BeforeEach
  public void setUp() {
    this.service = new LoginService();
    this.db = mock(Database.class);
  }

  @AfterEach
  public void tearDown() {
    this.service = null;
    this.result = false;
    this.db = null;
  }


  /*
   * Ensure that the user can log in with a username that 
   * exists and the corresponding correct password.
   */
  @Test
  public void userExistsValidPassword()
  {
    User testUser = new User("johnsmith", "JohnPassword", "Customer");
    Database.USERS.add(testUser);

    result = testUser.equals(service.login("johnsmith", "JohnPassword"));
    assertTrue(result);
  }

  /*
   * Ensure that the user cannot log in with a username that exists an 
   * incorrect corresponding password.
   */
  @Test
  public void userExistsInvalidPassword()
  {
    User testUser = new User("johnsmith", "JohnPassword", "Customer");
    db.USERS.add(testUser);

    result = testUser.equals(service.login("johnsmith", "password"));
    assertFalse(result);
  }

  /*
   * Ensure that the user cannot log in with a username that does not exist.
   */
  @Test
  public void invalidUser()
  {
    User testUser = null;
    User result = service.login("jojo", "password");
    assertEquals(result, testUser);
  }

  /*
   * Ensure that the user cannot log in with an empty username field.
   */
  @Test
  public void userExistsNoUsername()
  {
    User testUser = new User("johnsmith", "JohnPassword", "Customer");
    db.USERS.add(testUser);

    result = testUser.equals(service.login("", "password"));
    assertFalse(result);
  }

  /*
   * Ensure that the user cannot log in with an empty password field.
   */
  @Test
  public void userExistsNoPassword()
  {
    User testUser = new User("johnsmith", "JohnPassword", "Customer");
    db.USERS.add(testUser);

    result = testUser.equals(service.login("johnsmith", ""));
    assertFalse(result);
  }

}