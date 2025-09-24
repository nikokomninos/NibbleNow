package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nibblenow.app.MenuItem;
import com.nibblenow.app.services.MenuUpdateService;

public class MenuRemoveItemHandler implements HttpHandler {
  private final MenuUpdateService service = new MenuUpdateService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String itemName = parts[0];

    boolean result = service.removeItem("500 Degrees", itemName);
    String response = "";

    if (result == false) response = "Error: Item not removed";
    else response = "Item removed successfully"; 

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}