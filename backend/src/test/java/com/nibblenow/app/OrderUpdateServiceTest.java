/**
 * OrderUpdateServiceTest:
 * 
 * A JUnit test suite to test the OrderUpdateService class,
 * which contains methods related to updating an order's status
 * 
 * @author Nikolaos Komninos
 */

package com.nibblenow.app;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nibblenow.app.services.OrderUpdateService;

class OrderUpdateServiceTest {
  private OrderUpdateService service;
  private boolean result;
  private Database db;
  private Order expectedOrder, actualOrder;

  @BeforeEach
  public void setUp() {
    this.service = new OrderUpdateService();
    this.db = mock(Database.class);
    this.expectedOrder = null;
    this.actualOrder = null;
  }

  @AfterEach
  public void tearDown() {
    this.service = null;
    this.result = false;
    this.db = null;
    this.expectedOrder = null;
    this.actualOrder = null;
  }

  @Test
  public void removeOrderValid() {
    expectedOrder = new Order(
        "johnsmith",
        new ArrayList<MenuItem>(Arrays.asList(new MenuItem("Pizza", "Example"), new MenuItem("Hamburger", "Example"))));
    Order o = new Order(
        "johnsmith",
        new ArrayList<MenuItem>(Arrays.asList(new MenuItem("Pizza", "Example"), new MenuItem("Hamburger", "Example"))));
    this.db.ORDERS.put("500 Degrees", new ArrayList<Order>());
    db.ORDERS.get("500 Degrees").add(o);
    actualOrder = service.cancelOrder("johnsmith", o);
    assertTrue(expectedOrder.equals(actualOrder));
  }
}
