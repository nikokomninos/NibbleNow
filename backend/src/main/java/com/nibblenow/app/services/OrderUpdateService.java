package com.nibblenow.app.services;

import com.nibblenow.app.Order;
import com.nibblenow.app.Database;

import java.util.ArrayList;

/**
 * OrderUpdateService:
 * 
 * Contains methods related to updating an order's status
 * 
 * @author Nikolaos Komninos
 */
public class OrderUpdateService {

  /**
   * cancelOrder
   *
   * Cancels a customer's order,
   * removing it from a restaurant's list of orders
   *
   * @param username the username of the customer
   * @param order    the order being cancelled
   * @return an Order object of the order that was cancelled if
   *         it was cancelled successfully, null otherwise
   */
  public Order cancelOrder(String username, Order order) {
    ArrayList<Order> toRemove = new ArrayList<>();
    Order removed = null;
    for (Order o : Database.ORDERS.get("500 Degrees")) {
      if (o.getUsername().equals(username)) {
        removed = o;
        toRemove.add(o);
      }
    }
    Database.ORDERS.get("500 Degrees").removeAll(toRemove);
    return removed;
  }
}
