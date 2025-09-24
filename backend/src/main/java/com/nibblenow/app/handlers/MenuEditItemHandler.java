package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nibblenow.app.MenuItem;
import com.nibblenow.app.services.MenuUpdateService;

public class MenuEditItemHandler implements HttpHandler {
  private final MenuUpdateService service = new MenuUpdateService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String oldItemName = parts[0];
    String newItemName = parts[1];
    String newItemDescription = parts[2];

    if (newItemName.equals("Empty")) newItemName = "";
    if (newItemDescription.equals("Empty")) newItemDescription = "";

    MenuItem result = service.editItem("500 Degrees", oldItemName, newItemName, newItemDescription);
    String response = "";

    if (result == null) response = "Error: Item not edited";
    else response = "Item edited successfully"; 

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}