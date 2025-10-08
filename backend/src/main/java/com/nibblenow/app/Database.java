package com.nibblenow.app;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Database.java:
 * 
 * A class that contains static lists and hashmaps,
 * used to represent a "database"
 * 
 * @author Nikolaos Komninos, Christopher Sciortino
 */
public class Database {
  public static final ArrayList<User> USERS =  new ArrayList<User>();
  public static final HashMap<String, Restaurant> RESTAURANTS = new HashMap<String, Restaurant>();
  public static final HashMap<String, ArrayList<Order>> ORDERS = new HashMap<String, ArrayList<Order>>();
}
