package com.nibblenow.app;

import java.util.ArrayList;

/**
 * Restaurant:
 * 
 * An object representing a restaurant
 * 
 * @author Nikolaos Komninos
 */
public class Restaurant {
  private String name;
  private String type;
  private ArrayList<MenuItem> menu;

  /**
   * Constructor for the Restaurant class
   * @param name the name of the restaurant
   * @param type the type of restaurant it is
   */
  public Restaurant(String name, String type) {
    this.name = name;
    this.type = type;
    this.menu = new ArrayList<MenuItem>();
  }

  /**
   * Gets the name of a restaurant
   * @return the name of a restaurant
   */ 
  public String getName() {
    return this.name;
  }

  /**
   * Gets the type of a restaurant
   * @return the type of a restaurant
   */
  public String getType() {
    return this.type;
  }

  /**
   * Gets a restaurant's menu
   * @return a restaurant's menu
   */
  public ArrayList<MenuItem> getMenu() {
    return this.menu;
  }

  /**
   * Sets a restaurant's menu
   * @param menu the new menu
   */
  public void setMenu(ArrayList<MenuItem> menu) {
    this.menu = menu;
  }
}