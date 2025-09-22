package com.nibblenow.app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.nibblenow.app.services.RegisterService;

class RegisterServiceTest {

  private RegisterService service;
  private String result;
  private Database db;

  @BeforeEach
  public void setUp() {
    this.service = new RegisterService();
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
    assertEquals(null,service.register("","",""));

  }

  @Test
  public void testUserCreateAccountValidMinUsername(){

    User expectedUser = new User("Johns", "JohnPassword", "Customer");
    assertEquals(expectedUser, service.register("Johns", "JohnPassword", "Customer"));

  }
}