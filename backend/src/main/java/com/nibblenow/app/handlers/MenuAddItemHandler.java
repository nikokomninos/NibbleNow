package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nibblenow.app.MenuItem;
import com.nibblenow.app.services.MenuUpdateService;

/**
 * MenuAddItemHandler:
 * 
 * An HTTP endpoint that attempts to add an item to a
 * restaurant's menu, and returns the result
 */
public class MenuAddItemHandler implements HttpHandler {
  private final MenuUpdateService service = new MenuUpdateService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String itemName = parts[0];
    String itemDescription = parts[1];

    if (itemName.equals("Empty")) itemName = "";
    if (itemDescription.equals("Empty")) itemDescription = "";

    MenuItem result = service.addItem("500 Degrees", itemName, itemDescription);
    String response = "";

    if (result == null) response = "Error: Item not added";
    else response = "Item added successfully"; 

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}