package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.nibblenow.app.Database;
import com.nibblenow.app.Order;
import com.nibblenow.app.User;

/**
 * GetDriverOrdersHandler:
 *
 * An HTTP endpoint that returns all orders assigned to a specific driver
 */
public class GetDriverOrdersHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String response = "{ \"orders\": [";

    boolean firstOrder = true;
    for (Map.Entry<Order, User> entry : Database.DRIVER_ORDERS.entrySet()) {
      Order order = entry.getKey();
      User driver = entry.getValue();

      if (driver != null && driver.getUsername().equals(input)) {
        if (!firstOrder) {
          response += ",";
        } else {
          firstOrder = false;
        }

        response += "{ \"username\": \"" + order.getUsername() + "\", \"items\": [";
        for (int i = 0; i < order.getOrder().size(); i++) {
          if (i != order.getOrder().size() - 1) {
            response += "\"" + order.getOrder().get(i).getName() + "\",";
          } else {
            response += "\"" + order.getOrder().get(i).getName() + "\"";
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
