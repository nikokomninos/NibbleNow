package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.nibblenow.app.Database;
import com.nibblenow.app.MenuItem;

/**
 * GetMenuHandler:
 * 
 * An HTTP endpoint that returns a restaurant's full menu 
 */
public class GetMenuHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    ArrayList<MenuItem> menu = Database.RESTAURANTS.get(input).getMenu();

    // Build array of JSON objects, where the objects represent menu items
    String response = "{ \"menu\": [";
    for (int i = 0; i < menu.size(); i++) {
      if (i != menu.size() - 1) {
        response += "{ \"name\": \"" + menu.get(i).getName() + "\", \"description\": \"" + menu.get(i).getDescription() + "\" },";
      } else {
        response += "{ \"name\": \"" + menu.get(i).getName() + "\", \"description\": \"" + menu.get(i).getDescription() + "\" }";
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