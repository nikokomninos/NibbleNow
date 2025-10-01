package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.nibblenow.app.Cart;
import com.nibblenow.app.Database;
import com.nibblenow.app.MenuItem;
import com.nibblenow.app.User;

/**
 * GetCartHandler:
 * 
 * An HTTP endpoint that returns a user's cart
 */
public class GetCartHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    Cart cart = null;
    for (User user : Database.USERS) {
      if (user.getUsername().equals(input)) {
        cart = user.getCart();
        break;
      }
    }

    // Build array of JSON objects, where the objects represent menu items
    String response = "{ \"cart\": [";
    for (int i = 0; i < cart.getContents().size(); i++) {
      if (i != cart.getContents().size() - 1) {
        response += "{ \"name\": \"" + cart.getContents().get(i).getName() + "\", \"description\": \"" + cart.getContents().get(i).getDescription() + "\" },";
      } else {
        response += "{ \"name\": \"" + cart.getContents().get(i).getName() + "\", \"description\": \"" + cart.getContents().get(i).getDescription() + "\" }";
      }
    }
    response += "] }";

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.getResponseHeaders().add("Content-Type", "application/json");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}