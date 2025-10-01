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
import java.util.Arrays;

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
    this.user = new User("username", "password", "Customer", new Cart());
    db.ORDERS.put("500 Degrees", new ArrayList<Order>());
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
  @Test
  public void testCustomerRemoveItemFromOrderValidOneItem()
  {
    ArrayList<MenuItem> expectedCart = null;

    MenuItem pizza = new MenuItem("Pizza", "plain cheese pizza");
    
    
    ArrayList<MenuItem> actualCart = new ArrayList<MenuItem>();
    //User testUser = new User("johnsmith", "johnspassword", "Customer", actualCart);

  }



  /* TESTS FOR submitOrder() */

  /**
   * submitOrderTestStub:
   * 
   * Test stub for submitOrder()
   */
  @Test
  public void submitOrderTestStub() {
    actualOrder = service.submitOrder(user); 
    assertEquals(null, actualOrder);
  }

  /**
   * submitOrderOneValidItem: 
   * 
   * Ensure that the customer can submit their order with only one item in the cart
   */
  @Test
  public void submitOrderOneValidItem() {
    expectedOrder = new Order(user.getUsername(), new ArrayList<MenuItem>(Arrays.asList(new MenuItem("Pizza", "Example"))));
    Cart c = new Cart();
    c.setContents(new ArrayList<MenuItem>(Arrays.asList(new MenuItem("Pizza", "Example"))));
    user.setCart(c);
    actualOrder = service.submitOrder(user);
    assertTrue(expectedOrder.equals(actualOrder));
  }

  /**
   * submitOrderMultipleValidItem:
   * 
   * Ensure that the customer can submit their order with multiple items in the cart
   */
  @Test
  public void submitOrderMultipleValidItem() {
    expectedOrder = new Order(
        user.getUsername(), new ArrayList<MenuItem>(Arrays.asList(new MenuItem("Pizza", "Example"), new MenuItem("Hamburger", "Example"))));
    Cart c = new Cart();
    c.setContents(
        new ArrayList<MenuItem>(Arrays.asList(new MenuItem("Pizza", "Example"), new MenuItem("Hamburger", "Example"))));
    user.setCart(c);
    actualOrder = service.submitOrder(user);
    assertTrue(expectedOrder.equals(actualOrder));
  }

  /**
   * submitOrderEmptyCart:
   * 
   * Ensure that the customer cannot submit an order with an empty cart
   */
  @Test
  public void submitOrderEmptyCart() {
    user.setCart(new Cart());
    actualOrder = service.submitOrder(user);
    assertEquals(null, actualOrder);
  }

}