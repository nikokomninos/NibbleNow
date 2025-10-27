package com.nibblenow.app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import com.nibblenow.app.services.RegisterService;
/*
 * RegisterServiceTest:
 * 
 * This service is in charge of testing the Register service is working correctly.
 * 
 * @author Katarina Liedbeck
 */
class RegisterServiceTest {

  private RegisterService service;
  private boolean result;
  private Database db;

  @BeforeEach
  public void setUp() {
    this.service = new RegisterService();
    this.db = mock(Database.class);
  }

  @AfterEach
  public void tearDown() {
    this.service = null;
    this.result = false;
    this.db = null;
    this.db.USERS.clear();
  }

  /*
   * Testing the checkLength sub function 
   */
  @Test
  public void testCheckLength()
  {
    String username = "johnsmith";
    int min = 5;
    int max = 25;
    assertTrue(service.checkLength(username, min, max));
  }

  /*
   * Testing the checkSpecialChar sub function 
   */
  @Test
  public void testCheckSpecialChar()
  {
    String username = "johns";
    assertTrue(service.checkSpecialChar(username));
  }

  /*
   * Testing the checkUpperCase sub function 
   */
  @Test
  public void testCheckUpperCase()
  {
    String password = "johnspassword";
    assertFalse(service.checkUpperCase(password, 1));
  }

  /*
   * Ensure that we can create an account 
   * with the minimum amount of characters in the username.
   */
  @Test
  public void testUserCreateAccountValidMinUsername()
  {
    User expectedUser = new User("Johns", "JohnPassword", "Customer", new Cart());
    result = expectedUser.equals(service.register("Johns", "JohnPassword", "Customer"));
    assertTrue(result);
  }

  /*
   * Ensure that we can create an account 
   * with the maximum amount of characters in the username.
   */
  @Test
  public void testUserCreateAccountValidMaxUsername()
  {
    User expectedUser = new User("johnsmith1234567890123456", "JohnPassword", "Customer", new Cart());
    result = expectedUser.equals(service.register("johnsmith1234567890123456", "JohnPassword", "Customer"));
    assertTrue(result);
  }

  /* 
  * Ensure that we cannot create an account with 
  * less than the minimum characters (5) in the username.
  */
  @Test
  public void testUserCreateAccountInvalidMinUsername()
  {
    User response = service.register("john", "JohnPassword", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an account 
  * with more than the maximum characters (25) in the username.
  */
  @Test
  public void testUserCreateAccountInvalidMaxUsername()
  {
    User response = service.register("johnsmith12345678901234567", "JohnPassword", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an 
  * account with a space in the username.
  */
  @Test
  public void testUserCreateAccountInvalidContainsSpaceUsername()
  {
    User response = service.register("john smith", "JohnPassword", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an account 
  * with a special character in the username.
  */
  @Test
  public void testUserCreateAccountInvalidContainsSpecialUsername()
  {
    User response = service.register("johnsmith!", "JohnPassword", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an account 
  * with a username which is not unique regardless of the role.
  */
  @Test 
  public void testUserCreateAccountInvalidNotUniqueUsername()
  {
    User userNotUnique = new User("johnsmith", "pass", "Restaurant Owner", new Cart());
    db.USERS.add(userNotUnique);
    User response = service.register("johnsmith", "JohnPassword", "Customer");
    assertEquals(null,response);
  }

  /* 
  * Ensure that we cannot create an account with a 
  * username which is not unique within the same role.
  */
  @Test 
  public void testUserCreateAccountInvalidNotUniqueRoleUsername()
  {
    User userNotUnique = new User("johnsmith", "pass", "Customer", new Cart());
    db.USERS.add(userNotUnique);
    User response = service.register("johnsmith", "JohnPassword", "Customer");
    assertEquals(null,response);
  }

  /* 
  * Ensure that we cannot create an account 
  * with an empty username field.
  */
  @Test
  public void testUserCreateAccountInvalidNoUsernameUsername()
  {
    User response = service.register("", "JohnPassword", "Customer");
    assertEquals(null,response);
  }

  /* 
  * Ensure that we can create an account with the minimum 
  * number of characters (5) in the password. 
  */
  @Test
  public void testUserCreateAccountValidPasswordMin()
  {
    User expectedUser = new User("johnsmith", "JohnP", "Customer", new Cart());
    result = expectedUser.equals(service.register("johnsmith", "JohnP", "Customer"));
    assertTrue(result);
  }

  /* 
  * Ensure that we can create an account with the 
  * maximum number of characters (25) in the password. 
  */
  @Test
  public void testUserCreateAccountValidPasswordMax()
  {
    User expectedUser = new User("johnsmith", "JohnPassword1234567890123", "Customer", new Cart());
    result = expectedUser.equals(service.register("johnsmith", "JohnPassword1234567890123", "Customer"));
    assertTrue(result);
  }

  /* 
  * Ensure that we can create an account with only 
  * one uppercase character in the password.
  */
  @Test
  public void testUserCreateAccountValidPasswordUpper()
  {
    User expectedUser = new User("johnsmith", "Johnpassword", "Customer", new Cart());
    result = expectedUser.equals(service.register("johnsmith", "Johnpassword", "Customer"));
    assertTrue(result);
  }

  /* 
  * Ensure that we cannot create an account with less than 
  * the minimum number of characters (5) in the password
  */
  @Test
  public void testUserCreateAccountInvalidMinPassword()
  {
    User response = service.register("johnsmith", "John", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an account with more than the 
  * maximum number of characters (25) in the password
  */
  @Test
  public void testUserCreateAccountInvalidMaxPassword()
  {
    User response = service.register("johnsmith", "JohnPassword12345678901234", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an account with 
  * a no uppercase character in the password.
  */
  @Test
  public void testUserCreateAccountInvalidNoUppercasePassword()
  {
    User response = service.register("johnsmith", "johnpassword", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an account 
  * with a space in the password.
  */
  @Test
  public void testUserCreateAccountInvalidContainsSpacePassword()
  {
    User response = service.register("johnsmith", "John password", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an account 
  * with a special character in the password.
  */
  @Test
  public void testUserCreateAccountInvalidContainsSpecialPassword()
  {
    User response = service.register("johnsmith", "JohnPassword!", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an account 
  * with an empty password field.
  */
  @Test
  public void testUserCreateAccountInvalidEmptyPasswordPassword()
  {
    User response = service.register("johnsmith", "", "Customer");
    assertEquals(null,response); 
  }

  /* 
  * Ensure that we cannot create an 
  * account with no role selected.
  */
  @Test
  public void testUserCreateAccountInvalidSelectRole()
  {
    User response = service.register("johnsmith", "JohnPassword", "");
    assertEquals(null,response); 
  }


  /*
   * Here we are testing using MC/DC condition 4:
   * Username is NOT less than 25, Username is greater than 5 characters, username has special characters.
   */
  @Test
  public void testCreateAccountMCDCCaseFour()
  {
    User response = service.register("abcdefghijklmnopqrstuvwxyz!", "JohnPassword", "");
    assertEquals(null,response); 
  }

  /*
   * Here we are testing using MC/DC condition 6:
   * Username is less than 25, Username is NOT greater than 5 characters, username has special characters.
   */
  @Test
  public void testCreateAccountMCDCCaseSix()
  {
    User response = service.register("joh!", "JohnPassword", "");
    assertEquals(null,response); 
  }

  /*
   * Here we are testing using MC/DC condition 7:
   * Username is less than 25, Username is greater than 5 characters, username does NOT have special characters.
   */
  @Test
  public void testCreateAccountMCDCCaseSeven()
  {
    User response = service.register("johnny", "JohnPassword", "");
    assertEquals(null,response); 
  }

  /*
   * Here we are testing using MC/DC condition 8:
   * Username is less than 25, Username is greater than 5 characters, username does have special characters.
   */
  @Test
  public void testCreateAccountMCDCCaseEight()
  {
    User response = service.register("johnny!", "JohnPassword", "");
    assertEquals(null,response); 
  }

 
}