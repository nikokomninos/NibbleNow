package com.nibblenow.app;

import com.nibblenow.app.MenuItem;
import java.util.ArrayList;

/**
 * Order:
 * 
 * An object that represents a customer order
 * 
 * @author Nikolaos Komninos
 */
public class Order
{
  private String username;
  private ArrayList<MenuItem> order;

  /**
   * Constructor for the Order class
   * @param order an ArrayList of MenuItem objects
   */
  public Order(String username, ArrayList<MenuItem> order)
  {
    this.username = username;
    this.order = order;
  }

  /**
   * Returns the order
   * @return an ArrayList of MenuItem objects
   */
  public ArrayList<MenuItem> getOrder() {
    return this.order;
  }

  public String getUsername() {
    return this.username;
  }

  /**
   * Checks equality between two orders
   * @param order the order being compared to
   * @return true if equal, false otherwise
   */
  public boolean equals(Order order) {
    boolean b = false;
    try {
      for (int i = 0; i < this.order.size(); i++) {
        b = this.order.get(i).equals(order.getOrder().get(i));
      }
    } catch (Exception e) { b = false; }

    return b;
  }
}