package com.nibblenow.app.handlers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

import com.nibblenow.app.services.TestGetService;

/**
 * TestGetHandler:
 * 
 * A template for GET request endpoints
 */
public class TestGetHandler implements HttpHandler {
  private final TestGetService service = new TestGetService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    String response = service.testGet();
    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    exchange.sendResponseHeaders(200, response.length());
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}