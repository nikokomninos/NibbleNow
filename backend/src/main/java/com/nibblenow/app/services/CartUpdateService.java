package com.nibblenow.app.services;

import com.nibblenow.app.MenuItem;
import com.nibblenow.app.Order;
import com.nibblenow.app.User;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Random;

import com.nibblenow.app.Cart;
import com.nibblenow.app.Database;

/**
 * CartUpdateService:
 * 
 * Contains methods related to updating a user's cart
 * 
 * @author Nikolaos Komninos, Katarina Liedbeck, Christopher Sciortino
 */
public class CartUpdateService
{
  /**
   * @param user - this is a User object of the user that should have the MenuItem added to their cart.
   * @param item - this is a MenuItem that should be added to the users cart.
   * @return - a MenuItem that has been added to the user's cart or null if the user or item
   *           that was passed was null as then the item was not added to any user's cart.
   */
  public MenuItem addItemToCart(User user, MenuItem item)
  {
    // If no user or no menu item was passed, return null as the function can't run:
    if(user == null || item == null)
    {
      return(null);
    }

    // Create an Cart of menu items and get the cart from the user:
    Cart newCart = user.getCart();

    // Add the new item to our updated ArrayList:
    newCart.addToCart(item);

    // Update the user's cart to include the new added item:
    for (User u : Database.USERS) {
      if (u.equals(user)) user.setCart(newCart);
    }

    // Return the item that was added:
    return(item);
  }

  /**
   * @param user - this is a User object of the user that should have the MenuItem removed from their cart.
   * @param itemToBeRemoved - this is a MenuItem that should be removed from the users cart.
   * @return - a MenuItem that has been removed from the user's cart or null if the user or item
   *           that was passed was null as then the item was not added to any user's cart.
   */
  public MenuItem removeItemFromCart(User user, MenuItem itemToBeRemoved) {
    // If no user or no menu item was passed, return null as the function can't run:
    if(user == null || itemToBeRemoved == null || user.getCart().isEmpty())
    {
      return(null);
    }

    // Create a Cart of menu items and get the cart from the user:
    Cart newCart = user.getCart();

    // Remove the item from ArrayList:
    MenuItem mi = newCart.removeFromCart(itemToBeRemoved);

    // Update the user's cart to include the new added item:
    for (User u : Database.USERS) {
      if (u.equals(user)) user.setCart(newCart);
    }

    // Return the item that was added:
    return(mi);
  }

  /**
   * submitOrder:
   * 
   * Submits a user's order to the restaurant's list of orders,
   * clearing their cart in the process
   * 
   * @param user, the user whose order is being submitted
   * 
   * @return an Order object containing the customer's order
   */
  public Order submitOrder(User user) {
    if (user.getCart().isEmpty()) return null;
    else {
      Order o = new Order(user.getUsername(), user.getCart().getContents());
      Database.ORDERS.get("500 Degrees").add(o);

      user.setCart(new Cart());
      User driver = assignDriver();
      Database.DRIVER_ORDERS.put(o, driver);

      return o;
    }
  }

  /**
   * assignDriver
   *
   * Assigns a driver to a submitted order
   * (randomly chosen, for simulation purposes)
   */
  public User assignDriver() {
    List<User> drivers = Database.USERS.stream()
      .filter(user -> user.getRole().equals("Delivery Driver"))
      .collect(Collectors.toList());

    if (drivers.isEmpty()) return null;

    Random rand = new Random();
    int selectedDriver = rand.nextInt(drivers.size());
    return drivers.get(selectedDriver);
  }
}
