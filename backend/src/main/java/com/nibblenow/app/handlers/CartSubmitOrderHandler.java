package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nibblenow.app.Database;
import com.nibblenow.app.MenuItem;
import com.nibblenow.app.Order;
import com.nibblenow.app.User;
import com.nibblenow.app.services.CartUpdateService;

/**
 * CartSubmitOrderHandler:
 * 
 * An HTTP endpoint that attempts to submit a user's order,
 * and returns the result
 */
public class CartSubmitOrderHandler implements HttpHandler {
  private final CartUpdateService service = new CartUpdateService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String username = parts[0];

    User user = null;

    for (User u : Database.USERS) {
      if (u.getUsername().equals(username)) user = u;
    }

    Order result = service.submitOrder(user);
    String response = "";

    if (result == null) response = "Error: Order not submitted";
    else response = "Order submitted successfully"; 

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}