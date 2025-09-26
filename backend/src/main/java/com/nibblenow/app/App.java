package com.nibblenow.app;

import com.nibblenow.app.handlers.GetMenuHandler;
import com.nibblenow.app.handlers.LoginHandler;
import com.nibblenow.app.handlers.MenuAddItemHandler;
import com.nibblenow.app.handlers.MenuEditItemHandler;
import com.nibblenow.app.handlers.MenuRemoveItemHandler;
import com.nibblenow.app.handlers.RegisterHandler;
import com.nibblenow.app.handlers.TestGetHandler;
import com.nibblenow.app.handlers.TestPostHandler;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * App.java: 
 * 
 * Boostraps the API endpoints and backend server, using the
 * HttpServer class built into the JDK
 * 
 * @author Nikolaos Komninos
 */
public class App {

    private static final int PORT = 8000;

    /**
     * Bootsraps the API endpoints and starts the server
     * @param args
     */
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

            server.createContext("/api/getTest", new TestGetHandler());
            server.createContext("/api/postTest", new TestPostHandler());
            server.createContext("/api/login", new LoginHandler());
            server.createContext("/api/register", new RegisterHandler());
            server.createContext("/api/getMenu", new GetMenuHandler());
            server.createContext("/api/addItem", new MenuAddItemHandler());
            server.createContext("/api/editItem", new MenuEditItemHandler());
            server.createContext("/api/removeItem", new MenuRemoveItemHandler());

            server.setExecutor(null);
            server.start();
            System.out.println("Sever started on port " + PORT);

            // Populate with sample data
            populateUserDatabase();
            populateMenu();
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    /**
     * populateUserDatabase
     * 
     * Populates the user database with sample users
     * We used ChatGPT to generate some fake user data
     * for us to help us test our program.
     * 
     * Prompt: "Can you create 30 fake user following the given format. The roles are
     * Restaurant Owner, Customer, Delivery Driver. Usernames and passwords must
     * be between 5 and 25 characters, contain no spaces or special characters. 
     * Passwords must contain atleast one uppercase letter. Here is the format:
     * Database.USERS.add(new User("username", "password", "role", cart));"
     */
    public static void populateUserDatabase()
    {
        Cart cart = new Cart();
        Database.USERS.add(new User("testUser", "testPassword", "Customer", cart));
        Database.USERS.add(new User("testOwner", "testPassword", "Restaurant Owner", cart));
        
        Database.USERS.add(new User("jakeThompson22", "FastFoodLover1", "Customer", cart));
        Database.USERS.add(new User("mariaLopez", "StrongPass2", "Restaurant Owner", cart));
        Database.USERS.add(new User("driverDylan99", "DriveSafe7", "Delivery Driver", cart));
        Database.USERS.add(new User("samanthaGreen", "GreenKitchen3", "Restaurant Owner", cart));
        Database.USERS.add(new User("billyCook88", "BurgerTimeA1", "Restaurant Owner", cart));
        Database.USERS.add(new User("customerJenny", "BestChoiceB9", "Customer", cart));
        Database.USERS.add(new User("alexDriver", "ZoomZoomQ4", "Delivery Driver", cart));
        Database.USERS.add(new User("tinaTurner", "MusicMealsZ2", "Customer", cart));
        Database.USERS.add(new User("nathanCooks", "PanFryTuna5", "Restaurant Owner", cart));
        Database.USERS.add(new User("zoeMiller23", "TastyTreatK6", "Customer", cart));
        Database.USERS.add(new User("carlosDriver", "QuickRunU8", "Delivery Driver", cart));
        Database.USERS.add(new User("stephBaker", "OvenReadyW3", "Restaurant Owner", cart));
        Database.USERS.add(new User("happyCustomer", "GreatMealY7", "Customer", cart));
        Database.USERS.add(new User("graceParks", "DinnerTimeX5", "Restaurant Owner", cart));
        Database.USERS.add(new User("davidDeliver", "SpeedyG99", "Delivery Driver", cart));
        Database.USERS.add(new User("michaelChef", "PlateMasterA2", "Restaurant Owner", cart));
        Database.USERS.add(new User("lindaJones", "FoodFanZ8", "Customer", cart));
        Database.USERS.add(new User("harryDriver88", "FastTrackR6", "Delivery Driver", cart));
        Database.USERS.add(new User("restaurantEva", "KitchenProU3", "Restaurant Owner", cart));
        Database.USERS.add(new User("oliviaCustomer", "MealPassT4", "Customer", cart));
        Database.USERS.add(new User("noahSmith", "YummyDishK1", "Customer", cart));
        Database.USERS.add(new User("driverKyle", "DeliverQuickE7", "Delivery Driver", cart));
        Database.USERS.add(new User("lucasGrill", "HotGrillM2", "Restaurant Owner", cart));
        Database.USERS.add(new User("emmaFoodie", "SnackTimeF5", "Customer", cart));
        Database.USERS.add(new User("chefTony88", "MasterCookG3", "Restaurant Owner", cart));
        Database.USERS.add(new User("danielDrive", "RideOnX9", "Delivery Driver", cart));
        Database.USERS.add(new User("bellaCustomer", "FoodLoverV6", "Customer", cart));
        Database.USERS.add(new User("ethanKitchen", "PrepItUpB2", "Restaurant Owner", cart));
        Database.USERS.add(new User("driverNina", "RunOrderH4", "Delivery Driver", cart));
        Database.USERS.add(new User("kevinEats", "MealBoxZ1", "Customer", cart));


        System.out.println("User database populated!");
    }

    /**
     * populateMenu
     * Populates the menu of the restaurant '500 Degrees'
     * We used ChatGPT to generate some fake food data
     * for us to help us test our program.
     */
    public static void populateMenu()
    {
        Database.RESTAURANTS.put("500 Degrees", new Restaurant("500 Degrees", "Pizzeria"));
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
        menu.add(new MenuItem("Regular Pizza", "A classic pizza with tomato sauce and cheese."));
        menu.add(new MenuItem("Sicilian Pizza", "A thick-crust pizza with a variety of toppings."));
        menu.add(new MenuItem("Calzone", "A folded pizza filled with cheese and toppings."));
        menu.add(new MenuItem("Garlic Knots", "Dough knots topped with garlic and herbs."));
        Database.RESTAURANTS.get("500 Degrees").setMenu(menu);
        System.out.println("500 Degrees menu populated!");
    }
}
