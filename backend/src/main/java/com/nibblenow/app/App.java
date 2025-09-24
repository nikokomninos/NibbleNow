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

public class App {

    private static final int PORT = 8000;

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

            populateUserDatabase();
            populateMenu();
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    /*
     * We used ChatGPT to generate some fake user data
     * for us to help us test our program.
     */
    public static void populateUserDatabase() {
        Database.USERS.add(new User("testUser", "testPassword", "Customer"));
        Database.USERS.add(new User("testOwner", "testPassword", "Restaurant Owner"));
        Database.USERS.add(new User("alexRider99", "Fast@Food123", "Customer"));
        Database.USERS.add(new User("bellaChef22", "Cook&Roll9", "Restaurant Owner"));
        Database.USERS.add(new User("charlieDrive88", "Speed*Way7", "Delivery Driver"));
        Database.USERS.add(new User("daniDine55", "Forks#Up8", "Customer"));
        Database.USERS.add(new User("eddieEats21", "Munch!Time4", "Customer"));
        Database.USERS.add(new User("felixRunner", "Zoom&Deliver1", "Delivery Driver"));
        Database.USERS.add(new User("graceGrillz", "Hot$Grillz2", "Restaurant Owner"));
        Database.USERS.add(new User("hannahTaste", "Yum@Plate3", "Customer"));
        Database.USERS.add(new User("ianDash90", "Move#It5", "Delivery Driver"));
        Database.USERS.add(new User("jessieSpice", "Spicy*Queen7", "Restaurant Owner"));
        Database.USERS.add(new User("kyleZoomer", "Fast$Feet8", "Delivery Driver"));
        Database.USERS.add(new User("lunaLunch", "Lunch#Box5", "Customer"));
        Database.USERS.add(new User("mattBurger", "Burg@King9", "Restaurant Owner"));
        Database.USERS.add(new User("ninaQuick", "Run&Drop2", "Delivery Driver"));
        Database.USERS.add(new User("owenEater", "Full$Belly6", "Customer"));
        Database.USERS.add(new User("paulaPan", "Fry!Master3", "Restaurant Owner"));
        Database.USERS.add(new User("quentinDash", "Zip&Zap7", "Delivery Driver"));
        Database.USERS.add(new User("rachelBites", "Chomp@Chomp1", "Customer"));
        Database.USERS.add(new User("samSizzle", "Grill#Time2", "Restaurant Owner"));
        Database.USERS.add(new User("tonyDrive", "Rush*Rush5", "Delivery Driver"));
        System.out.println("User database populated!");
    }

    /*
     * We used ChatGPT to generate some menu items
     * for us to help us test our program.
     */
    public static void populateMenu() {
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
