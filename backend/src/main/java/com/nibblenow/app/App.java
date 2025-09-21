package com.nibblenow.app;

import com.nibblenow.app.handlers.LoginHandler;
import com.nibblenow.app.handlers.TestGetHandler;
import com.nibblenow.app.handlers.TestPostHandler;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

    private static final int PORT = 8000; 

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

            server.createContext("/api/getTest", new TestGetHandler());
            server.createContext("/api/postTest", new TestPostHandler());
            server.createContext("/api/login", new LoginHandler());

            server.setExecutor(null);
            server.start();
            System.out.println("Sever started on port " + PORT);

            Database.USERS.put("testuser", "TestPass");
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}
