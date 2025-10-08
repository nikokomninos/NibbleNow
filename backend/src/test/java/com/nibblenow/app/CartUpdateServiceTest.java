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

/**
 * CartUpdateServiceTest:
 * 
 * This service is in charge of testing the cart update service is working correctly.
 * 
 * @author Christopher Sciortino, Nikolaos Komninos, Katarina Liedbeck
 */
class CartUpdateServiceTest
{

  private CartUpdateService service;
  private MenuItem expected, actual;
  private Order expectedOrder, actualOrder;
  private Database db;
  private User user;

  @BeforeEach
  public void setUp() {
    this.service = new CartUpdateService();
    this.db = mock(Database.class);
    this.expected = null;
    this.actual = null;
    this.user = new User("username", "password", "Customer", new Cart());
    db.ORDERS.put("500 Degrees", new ArrayList<Order>());
  }

  @AfterEach
  public void tearDown() {
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
  public void customerAddItemToValidEmptyCart() {
    Cart actualCart = new Cart();
    Cart expectedCart = new Cart();

    MenuItem itemToAdd = new MenuItem("Regular Pizza", "A classic pizza with tomato sauce and cheese.");

    expectedCart.addToCart(itemToAdd);

    if (this.user.getCart().isEmpty() == true) {
      MenuItem itemAdded = this.service.addItemToCart(this.user, itemToAdd);

      actualCart = this.user.getCart();
    }

    boolean isEqual = actualCart.isEqual(expectedCart);
    assertTrue(isEqual);
  }

  /*
  * This test ensure that the customer can add only one 
  * item to their order with other items in their cart.
  */
  @Test
  public void customerAddItemToOrderValidNotEmptyCart()
  {
    Cart actualCart = new Cart();
    Cart expectedCart = new Cart();

    MenuItem pizza = new MenuItem("Regular Pizza", "A classic pizza with tomato sauce and cheese.");
    MenuItem hamburger = new MenuItem("Hamburger", "A juicy hamburger with a toasted bun.");

    expectedCart.addToCart(pizza);
    expectedCart.addToCart(hamburger);
    
    if(this.user.getCart().isEmpty() == true)
    {
      MenuItem itemAdded = this.service.addItemToCart(this.user, pizza);

      actualCart = this.user.getCart();
    }

    this.service.addItemToCart(this.user, hamburger);

    boolean isEqual = actualCart.isEqual(expectedCart);
    assertTrue(isEqual);
  }


  /*
  * This test ensure that the customer can add the
  * maximum amount of items to their order, which is 10.
  */
  @Test
  public void customerAddItemToOrderValidMaxItems()
  {
    Cart actualCart = new Cart();
    Cart expectedCart = new Cart();

    MenuItem pizza = new MenuItem("Regular Pizza", "A classic pizza with tomato sauce and cheese.");

    for(int i = 0; i < 10; i ++)
    {
      expectedCart.addToCart(pizza);
    }
    
    
    if(this.user.getCart().isEmpty() == true)
    {
      for(int i = 0; i < 10; i ++)
      {
        MenuItem itemAdded = this.service.addItemToCart(this.user, pizza);
      }
      
      actualCart = this.user.getCart();
    }

    boolean isEqual = actualCart.isEqual(expectedCart);
    assertTrue(isEqual);
  }

    /*
  * This test ensure that the customer can add the
  * maximum amount of items to their order, which is 10.
  */
  @Test
  public void customerAddItemToOrderInvalidMoreThanMax()
  {
    Cart actualCart = new Cart();
    Cart expectedCart = new Cart();

    MenuItem pizza = new MenuItem("Regular Pizza", "A classic pizza with tomato sauce and cheese.");

    for(int i = 0; i < 10; i ++)
    {
      expectedCart.addToCart(pizza);
    }
    
    MenuItem itemAdded = new MenuItem("notnull", "notnull");

    if(this.user.getCart().isEmpty() == true)
    {
      for(int i = 0; i < 11; i ++)
      {
        itemAdded = this.service.addItemToCart(this.user, pizza);
      }
    }

    assertTrue(itemAdded != null);
  }

  /* TESTS FOR removeItemFromCart() */
  /*
   * Ensure that the customer can remove an
   * item from their order, with only one item in their cart.
   */
  @Test
  public void testCustomerRemoveItemFromOrderValidOneItem() {
    // Adding to, then removing from the users cart.
    Cart actualCart = this.user.getCart();
    MenuItem pizza = new MenuItem("Pizza", "plain cheese pizza");
    actualCart.addToCart(pizza);
    service.removeItemFromCart(this.user, pizza);

    boolean isEmpty = false;

    // The users cart should now be empty.
    if (actualCart.isEmpty()) {
      isEmpty = true;
    }
    assertTrue(isEmpty);
  }

  /*
   * Ensure that the customer can remove an
   * item from their order, with multiple items in their cart.
   */
  @Test
  public void testCustomerRemoveItemFromOrderValidMultipleItems() {
    // Adding both the pizza and hamburger to the users cart, only removing the
    // pizza.
    Cart actualCart = this.user.getCart();
    MenuItem pizza = new MenuItem("Pizza", "plain cheese pizza");
    MenuItem hamburger = new MenuItem("Hamburger", "juicy hamburger");
    actualCart.addToCart(pizza);
    actualCart.addToCart(hamburger);
    service.removeItemFromCart(this.user, pizza);

    // Expected cart should just have a hamburger.
    Cart expectedCart = new Cart();
    expectedCart.addToCart(hamburger);

    boolean isEqual = false;

    if (actualCart.isEqual(expectedCart)) {
      isEqual = true;
    }
    assertTrue(isEqual);
  }

  /*
   * Ensure that the customer cannot
   * remove from an empty cart.
   */
  @Test
  public void testCustomerRemoveItemFromOrderInvalidNoItems() {
    MenuItem pizza = new MenuItem("Pizza", "plain cheese pizza");
    MenuItem outcome = service.removeItemFromCart(this.user, pizza);

    boolean isNull = false;

    // If value returned from trying to remove is null
    if (outcome == null) {
      isNull = true;
    }
    assertTrue(isNull);
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
   * Ensure that the customer can submit their order with only one item in the
   * cart
   */
  @Test
  public void submitOrderOneValidItem() {
    expectedOrder = new Order(user.getUsername(),
        new ArrayList<MenuItem>(Arrays.asList(new MenuItem("Pizza", "Example"))));
    Cart c = new Cart();
    c.setContents(new ArrayList<MenuItem>(Arrays.asList(new MenuItem("Pizza", "Example"))));
    user.setCart(c);
    actualOrder = service.submitOrder(user);
    assertTrue(expectedOrder.equals(actualOrder));
  }

  /**
   * submitOrderMultipleValidItem:
   * 
   * Ensure that the customer can submit their order with multiple items in the
   * cart
   */
  @Test
  public void submitOrderMultipleValidItem() {
    expectedOrder = new Order(
        user.getUsername(),
        new ArrayList<MenuItem>(Arrays.asList(new MenuItem("Pizza", "Example"), new MenuItem("Hamburger", "Example"))));
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
