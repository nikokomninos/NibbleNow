package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import com.nibblenow.app.Database;
import com.nibblenow.app.MenuItem;
import com.nibblenow.app.Order;
import com.nibblenow.app.services.OrderUpdateService;
import com.nibblenow.app.MenuItem;

/**
 * CancelOrderHandler:
 * 
 * An HTTP endpoint that cancels a customer's order
 */
public class CancelOrderHandler implements HttpHandler {
  private final OrderUpdateService service = new OrderUpdateService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String restaurant = parts[0];
    String username = parts[1];
    String orderText = parts[2];

    String[] items = orderText.split("&");
    ArrayList<MenuItem> orderItems = new ArrayList<MenuItem>();
    for (String i : items) {
      orderItems.add(new MenuItem(i, ""));
    }

    Order order = new Order(username, orderItems);

    String response = "";
    Order removed = service.cancelOrder(username, order);
    if (removed == null) response = "Order could not be cancelled";
    else response = "Order cancelled successfully";

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.getResponseHeaders().add("Content-Type", "application/json");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}
