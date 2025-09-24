package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nibblenow.app.services.RegisterService;
import com.nibblenow.app.User;

/**
 * RegisterHandler:
 * 
 * An HTTP endpoint that attempts to register a
 * new user, and returns the result 
 */
public class RegisterHandler implements HttpHandler {
  private final RegisterService service = new RegisterService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String username = parts[0];
    String password = parts[1];
    String role = parts[2];

    if (username.equals("Empty")) username = "";
    if (password.equals("Empty")) password = "";
    if (role.equals("Empty")) role = "";

    User result = service.register(username, password, role);
    String status = "";
    String response = "";
    if (result == null) {
      status = "Error: Invalid credentials";
      response = "{ \"status\": \"" + status + "\" }";
    } else {
      status = "Account created successfully";
      response = "{ \"status\": \"" + status + "\", \"username\": \"" + result.getUsername() + "\", \"password\": \""
          + result.getPassword() + "\", \"role\": \"" + result.getRole() + "\" }";
    }

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.getResponseHeaders().add("Content-Type", "application/json");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}