package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nibblenow.app.Database;
import com.nibblenow.app.MenuItem;
import com.nibblenow.app.User;
import com.nibblenow.app.services.CartUpdateService;

/**
 * CartAddItemHandler:
 * 
 * An HTTP endpoint that attempts to add an item to a
 * customer's cart, and returns the result
 */
public class CartAddItemHandler implements HttpHandler {
  private final CartUpdateService service = new CartUpdateService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String username = parts[0];
    String itemName = parts[1];
    String itemDescription = parts[1];

    User user = null;
    MenuItem item = null;

    for (User u : Database.USERS) {
      if (u.getUsername().equals(username)) user = u;
    }

    if (!itemName.isEmpty() && !itemDescription.isEmpty()) item = new MenuItem(itemName, itemDescription);

    MenuItem result = service.addItemToCart(user, item);
    String response = "";

    if (result == null) response = "Error: Item not added to cart";
    else response = "Item added successfully to cart"; 

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}