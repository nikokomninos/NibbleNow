package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.nibblenow.app.Database;
import com.nibblenow.app.MenuItem;
import com.nibblenow.app.Order;
import com.nibblenow.app.User;

/**
 * GetOrdersHandler:
 * 
 * An HTTP endpoint that returns a restaurant's order list
 */
public class GetOrdersHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    ArrayList<Order> orders = Database.ORDERS.get(input);

    // Build array of JSON objects, where the objects represent orders
    String response = "{ \"orders\": [";
    for (int i = 0; i < orders.size(); i++) {
      Order order = orders.get(i);

      // Get driver assigned to this order
      User driver = Database.DRIVER_ORDERS.get(order);
      String driverName = (driver != null) ? driver.getUsername() : "Unassigned";

      response += "{ \"username\": \"" + order.getUsername() + "\", ";
      response += "\"driver\": \"" + driverName + "\", ";
      response += "\"items\": [";

      for (int j = 0; j < order.getOrder().size(); j++) {
        response += "\"" + order.getOrder().get(j).getName() + "\"";
        if (j != order.getOrder().size() - 1) {
          response += ",";
        }
      }

      response += "] }";

      if (i != orders.size() - 1) {
        response += ",";
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
