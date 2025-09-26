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

class CartUpdateServiceTest
{

  private CartUpdateService service;
  private MenuItem expected, actual;
  private Order expectedOrder, actualOrder;
  private Database db;
  private User user;

  @BeforeEach
  public void setUp()
  {
    this.service = new CartUpdateService();
    this.db = mock(Database.class);
    this.expected = null;
    this.actual = null;
<<<<<<< HEAD
    this.user = mock(User.class);
=======
    this.expectedOrder = null;
    this.actualOrder = null;
    this.user = null;
>>>>>>> 5bc7fd4 (feat: start submitOrder tests)
  }

  @AfterEach
  public void tearDown()
  {
    this.service = null;
    this.expected = null;
    this.actual = null;
    this.expectedOrder = null;
    this.actualOrder = null;
    this.db = null;
    this.user = null;
  }

  /* TESTS FOR addItemToCart() */
  /*
   * This test ensure that the customer can add only one 
   * item to their order with nothing in their cart yet.
   */
  @Test
  public void customerAddItemToValidEmptyCart()
  {
    Cart actualCart = new Cart();
    Cart expectedCart = new Cart();

    MenuItem itemToAdd = new MenuItem("Regular Pizza", "A classic pizza with tomato sauce and cheese.");

    expectedCart.addToCart(itemToAdd);
    
    if(this.user.getCart().isEmpty() == true)
    {
      MenuItem itemAdded = this.service.addItemToCart(this.user, itemToAdd);

      actualCart = this.user.getCart();
    }

    boolean isEqual = actualCart.isEqual(expectedCart);
    assertTrue(isEqual);
  }

  /* TESTS FOR removeItemFromCart() */


  /* TESTS FOR submitOrder() */

  @Test
  public void submitOrderTestStub() {
    actualOrder = service.submitOrder(); 
    assertEquals(null, actualOrder);
  }

}