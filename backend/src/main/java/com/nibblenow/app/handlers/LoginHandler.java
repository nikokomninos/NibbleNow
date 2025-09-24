package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nibblenow.app.User;
import com.nibblenow.app.services.LoginService;

/**
 * LoginHandler:
 * 
 * An HTTP endpoint that attempts to log a user in,
 * and returns the result 
 */
public class LoginHandler implements HttpHandler {
  private final LoginService service = new LoginService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();
    byte[] data = is.readAllBytes();
    String input = new String(data).trim();

    String[] parts = input.split(",");
    String username = parts[0];
    String password = parts[1];

    if (username.equals("Empty")) username = "";
    if (password.equals("Empty")) password = "";

    User result = service.login(username, password);
    String status = "";
    String response = "";
    if (result == null) {
      status = "Error: Invalid credentials";
      response = "{ \"status\": \"" + status + "\" }";
    } else {
      status = "Success";
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