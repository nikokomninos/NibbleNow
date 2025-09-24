/**
 * MenuUpdateService:
 * 
 * Contains methods related to updating a restaurant's menu
 * 
 * @author Nikolaos Komninos
 */

package com.nibblenow.app.services;

import java.util.ArrayList;
import java.util.Iterator;

import com.nibblenow.app.Database;
import com.nibblenow.app.MenuItem;
import com.nibblenow.app.Restaurant;

/**
 * MenuUpdateService:
 * 
 * A service that handles functionality relating
 * to updating a restaurant's menu
 */
public class MenuUpdateService {

  /**
   * addItem:
   * 
   * Adds a MenuItem object to a restaurant's menu
   * 
   * @param restaurant, the name of a restaurant; must not be empty,
   *  must exist in the database
   * @param itemName; must be between 5 and 25 characters long
   * @param itemDescription; must be up to 50 characters long
   * @return the newly added MenuItem as confirmation, null otherwise
   */
  public MenuItem addItem(String restaurant, String itemName, String itemDescription) {
    ArrayList<MenuItem> menu;
    MenuItem menuItem = null;

    // Checks if restaurant exists in the database
    try { menu = Database.RESTAURANTS.get(restaurant).getMenu(); }
    catch (NullPointerException e) { return null; }

    // Checks for itemName and itemDescription requirements
    if (itemName.length() < 5 || itemName.length() > 25) return null;
    if (itemDescription.length() > 50) return null;

    menu.add(new MenuItem(itemName, itemDescription));
    Database.RESTAURANTS.get(restaurant).setMenu(menu);

    // Checks the newly upated menu to see if the new MenuItem was actually added
    for (MenuItem item : Database.RESTAURANTS.get(restaurant).getMenu()) {
      if (item.getName().equals(itemName) && item.getDescription().equals(itemDescription)) {
        menuItem = item;
      }
    }

    return menuItem;
  }

  /**
   * removeItem:
   * 
   * Removes a specified MenuItem object from a restaurant's menu
   * 
   * @param restaurant, the name of a restaurant; must not be empty,
   *  must exist in the database
   * @param itemName; must be between 5 and 25 characters long
   * @return true if the removal was successful, false otherwise
   */
  public boolean removeItem(String restaurant, String itemName) {
    ArrayList<MenuItem> menu;
    boolean isRemoved = false;
    Iterator<MenuItem> it; // Iterator for safe removals

    // Checks if restaurant exists in the database
    try { menu = Database.RESTAURANTS.get(restaurant).getMenu(); }
    catch (NullPointerException e) { return false; }

    it = menu.iterator();

    // Looks for MenuItem attempting to be removed; removes if found
    while(it.hasNext()) {
      MenuItem item = it.next();
      if (item.getName().equals(itemName)) {
        isRemoved = menu.remove(item);
        break;
      }
    }

    Database.RESTAURANTS.get(restaurant).setMenu(menu);

    return isRemoved;
  }

  /**
   * editItem:
   * 
   * Edits existing MenuItem in a restaurant's menu
   * 
   * @param restaurant, the name of a restaurant; must not be empty,
   *  must exist in the database
   * @param itemName; original item name 
   * @param newItemName; must be between 5 and 25 characters long
   * @param newItemDescription; must be up to 50 characters long
   * @return the updated MenuItem as confirmation, null otherwise
   */
  public MenuItem editItem(String restaurant, String itemName, String newItemName, String newItemDescription) {
    ArrayList<MenuItem> menu;
    MenuItem menuItem = null;

    // Checks if restaurant exists in the database
    try { menu = Database.RESTAURANTS.get(restaurant).getMenu(); }
    catch (NullPointerException e) { return null; }

    // Checks for itemName and itemDescription requirements
    if (newItemName.length() < 5 || newItemName.length() > 25) return null;
    if (newItemDescription.length() > 50) return null;

    // Updates the MenuItem's name and description
    for (MenuItem item : menu) {
      if (item.getName().equals(itemName)) {
        item.setName(newItemName);
        // Description only updates if it has been changed
        if(!newItemDescription.equals(item.getDescription())) { 
          item.setDescription(newItemDescription); 
        }
      }
    }

    Database.RESTAURANTS.get(restaurant).setMenu(menu);

    // Checks the newly upated menu to see if the edited MenuItem was actually changed
    for (MenuItem item : Database.RESTAURANTS.get(restaurant).getMenu()) {
      if (item.getName().equals(newItemName) && item.getDescription().equals(newItemDescription)) {
        menuItem = item;
      }
    }

    return menuItem;
  }
}