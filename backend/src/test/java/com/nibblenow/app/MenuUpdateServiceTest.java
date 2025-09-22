/**
 * MenuUpdateServiceTest:
 * 
 * A JUnit test suite to test the MenuUpdateService class,
 * which contains methods related to updating a restaurant's menu
 * 
 * @author Nikolaos Komninos
 */


package com.nibblenow.app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.nibblenow.app.services.MenuUpdateService;

class MenuUpdateServiceTest {

  private MenuUpdateService service;
  private boolean result;
  private Database db;

  @BeforeEach
  public void setUp() {
    this.service = new MenuUpdateService();
    this.db = mock(Database.class);
  }

  @AfterEach
  public void tearDown() {
    this.service = null;
    this.result = false;
    this.db = null;
  }

  /* TESTS FOR addItem() */

  @Test
  public void addItemTestStub() {
    result = service.addItem();
    assertEquals(false, result);
  }

  @Test
  public void addItemMinNameLength() {
    result = service.addItem();
    assertEquals(true, result);
  }

  /* TESTS FOR removeItem() */

  /* TESTS FOR editItem() */
}