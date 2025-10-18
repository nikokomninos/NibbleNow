package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.nibblenow.app.Database;
import com.nibblenow.app.Order;
import com.nibblenow.app.User;

/**
 * GetCustomerOrdersHandler:
 * 
 * An HTTP endpoint that returns a customer's order list
 */
public class GetCustomerOrdersHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String restaurant = parts[0];
    String username = parts[1];

    ArrayList<Order> orders = Database.ORDERS.get(restaurant);

    // Build array of JSON objects, where the objects represent matching orders
    String response = "{ \"orders\": [";

    boolean first = true;
    for (int i = 0; i < orders.size(); i++) {
      Order order = orders.get(i);

      if (order.getUsername().equals(username)) {
        if (!first) {
          response += ",";
        }
        first = false;

        // Get the driver assigned to this order
        User driver = Database.DRIVER_ORDERS.get(order);
        String driverName = (driver != null) ? driver.getUsername() : "Unassigned";

        // Start JSON object for this order
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
