
package com.nibblenow.app.services;

import java.util.ArrayList;

import com.nibblenow.app.MenuItem;
import com.nibblenow.app.Order;
import com.nibblenow.app.User;

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

    // Create an ArrayList of menu items and get the cart from the user:
    ArrayList<MenuItem> newCart = user.getCart();

    // Add the new item to our updated ArrayList:
    newCart.add(item);

    // Update the user's cart to include the new added item:
    user.setCart(newCart);

    // Return the item that was added:
    return(item);
  }

  
  public MenuItem removeItemFromCart() {
    return null;
  }

  public Order submitOrder() {
    return null;
  }
}