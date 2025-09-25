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
public class Order {
  private ArrayList<MenuItem> order;

  /**
   * Constructor for the Order class
   * @param order an ArrayList of MenuItem objects
   */
  public Order(ArrayList<MenuItem> order) {
    this.order = order;
  }

  /**
   * Returns the order
   * @return an ArrayList of MenuItem objects
   */
  public ArrayList<MenuItem> getOrder() {
    return this.order;
  }
}