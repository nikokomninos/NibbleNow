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
    for(int i = 0; i < orders.size(); i++) {
      if (i != orders.size() - 1) {
        response += "{ \"username\": \"" + orders.get(i).getUsername() + "\", \"items\": [";
        for (int j = 0; j < orders.get(i).getOrder().size(); j++) {
          if (j != orders.get(i).getOrder().size() - 1) {
            response += "\"" + orders.get(i).getOrder().get(j).getName() + "\","; 
          } else {
            response += "\"" + orders.get(i).getOrder().get(j).getName() + "\""; 
          }
        }
        response += "] },";
      } else {
        response += "{ \"username\": \"" + orders.get(i).getUsername() + "\", \"items\": [";
        for (int j = 0; j < orders.get(i).getOrder().size(); j++) {
          if (j != orders.get(i).getOrder().size() - 1) {
            response += "\"" + orders.get(i).getOrder().get(j).getName() + "\","; 
          } else {
            response += "\"" + orders.get(i).getOrder().get(j).getName() + "\""; 
          }
        }
        response += "] }";
      }
    }
    response += "] }";
    System.out.println(response);

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.getResponseHeaders().add("Content-Type", "application/json");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}