package com.nibblenow.app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.nibblenow.app.services.LoginService;

class LoginServiceTest {

  private LoginService service;
  private String result;
  private Database db;

  @BeforeEach
  public void setUp() {
    this.service = new LoginService();
    this.db = mock(Database.class);
  }

  @AfterEach
  public void tearDown() {
    this.service = null;
    this.result = null;
    this.db = null;
  }

  @Test
  public void testStub() {
    assertEquals("", service.login("", ""));
  }

  @Test
  public void userExistsValidPassword() {
    this.db.USERS.put("johnsmith", "JohnPassword");
  }

  @Test
  public void userExistsInvalidPassword() {
  }

  @Test
  public void invalidUser() {
  }
}