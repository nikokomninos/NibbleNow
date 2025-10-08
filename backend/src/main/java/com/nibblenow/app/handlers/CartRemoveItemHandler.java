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
 * CartRemoveItemHandler:
 * 
 * An HTTP endpoint that attempts to remove an item from a
 * customer's cart, and returns the result
 */
public class CartRemoveItemHandler implements HttpHandler {
  private final CartUpdateService service = new CartUpdateService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String username = parts[0];
    String itemName = parts[1];
    String itemDescription = parts[2];

    User user = null;
    MenuItem item = null;

    for (User u : Database.USERS) {
      if (u.getUsername().equals(username)) user = u;
    }

    if (!itemName.isEmpty() && !itemDescription.isEmpty()) item = new MenuItem(itemName, itemDescription);

    MenuItem result = service.removeItemFromCart(user, item);
    String response = "";

    if (result == null) response = "Error: Item not removed from cart";
    else response = "Item removed successfully from cart"; 

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}
