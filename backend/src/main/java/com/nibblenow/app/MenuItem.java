package com.nibblenow.app;

public class MenuItem {
  private String name;
  private String description;

  public MenuItem(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean equals(MenuItem other) {
    if (other == null) { return false; }
    if (other.getName().equals(this.name) &&
        other.getDescription().equals(this.description)) { return true; }
    else return false;
  }
}