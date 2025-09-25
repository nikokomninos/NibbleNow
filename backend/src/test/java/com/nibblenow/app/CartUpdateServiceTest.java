/**
 * CartUpdateServiceTest:
 * 
 * A JUnit test suite to test the CartUpdateServiceTest class,
 * which contains methods related to updating a user's cart
 * 
 * @author Nikolaos Komninos, Katarina Liedbeck, Christopher Sciortino
 */

package com.nibblenow.app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nibblenow.app.services.CartUpdateService;
import com.nibblenow.app.MenuItem;

import java.util.ArrayList;

class CartUpdateServiceTest {

  private CartUpdateService service;
  private MenuItem expected, actual;
  private Database db;
  private User user;

  @BeforeEach
  public void setUp() {
    this.service = new CartUpdateService();
    this.db = mock(Database.class);
    db.RESTAURANTS.put("Pizzaria", new Restaurant("Pizzaria", "A pizzaria"));
    ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    menu.add(new MenuItem("Regular Pizza", "A classic pizza with tomato sauce and cheese."));
    menu.add(new MenuItem("Sicilian Pizza", "A thick-crust pizza with a variety of toppings."));
    menu.add(new MenuItem("Calzone", "A folded pizza filled with cheese and toppings."));
    menu.add(new MenuItem("Garlic Knots", "Dough knots topped with garlic and herbs."));
    Database.RESTAURANTS.get("500 Degrees").setMenu(menu);
    this.expected = null;
    this.actual = null;
    this.user = null;
  }

  @AfterEach
  public void tearDown() {
    this.service = null;
    this.expected = null;
    this.actual = null;
    this.db = null;
    this.user = null;
  }

  /* TESTS FOR addItemToCart() */


  /* TESTS FOR removeItemFromCart() */


  /* TESTS FOR submitOrder() */

}