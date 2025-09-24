package com.nibblenow.app;

/**
 * MenuItem:
 * 
 * An object that represents a menu item
 * on a restaurant's menu
 * 
 * @author Nikolaos Komninos
 */
public class MenuItem {
  private String name;
  private String description;

  /**
   * Constructor for the MenuItem class
   * @param name the name of the menu item
   * @param description the description of the menu item
   */
  public MenuItem(String name, String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * Get the name of a menu item
   * @return the name of the menu item
   */
  public String getName() {
    return this.name;
  }

  /**
   * Set the name of a menu item
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the description of a menu item
   * @return the description of a menu item
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Set the description of a menu item
   * @param description the description of a menu item
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * A custom equals method to check if one
   * menu item and equal to another
   * @param other the menu item being checked for equality
   * @return true if equal, false otherwise
   */
  public boolean equals(MenuItem other) {
    if (other == null) { return false; }
    if (other.getName().equals(this.name) &&
        other.getDescription().equals(this.description)) { return true; }
    else return false;
  }
}