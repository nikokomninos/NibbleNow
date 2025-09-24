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

  public Restaurant(String name, String type) {
    this.name = name;
    this.type = type;
    this.menu = new ArrayList<MenuItem>();
  }

  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.type;
  }

  public ArrayList<MenuItem> getMenu() {
    return this.menu;
  }

  public void setMenu(ArrayList<MenuItem> menu) {
    this.menu = menu;
  }
}