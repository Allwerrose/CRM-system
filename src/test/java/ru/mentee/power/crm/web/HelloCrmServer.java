package ru.mentee.power.crm.web;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class HelloCrmServer {

  private final HttpServer server;

  public HelloCrmServer(int port) throws IOException {
    if (port < 1 || port > 65535) {
      throw new IllegalArgumentException("Port must be between 1 and 65535");
    }
      this.server = HttpServer.create(new InetSocketAddress(port), 0);
  }

  public void start() {
    try {
      server.createContext("/hello", new HelloHandler());
      server.start();
      //System.out.println(" Server started on http://localhost:" + port);
    } catch (Exception e) {
      throw new RuntimeException("Failed to start server", e);
    }
  }

  public void stop() {
    if (server != null) {
      server.stop(0);
      System.out.println("Server stopped");
    }
  }

  static class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
      String method = exchange.getRequestMethod();
      String path = exchange.getRequestURI().getPath();
      System.out.println("Received " + method + " request for " + path);
      String html = "<!DOCTYPE html><html><body><h1>Hello CRM!!</h1></body></html>";

      exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
      byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
      exchange.sendResponseHeaders(200, bytes.length);

      try (OutputStream os = exchange.getResponseBody()) {
        os.write(bytes);
      }
    }
  }
}