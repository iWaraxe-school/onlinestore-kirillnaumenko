package com.coherentsolutions.services.httpHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public abstract class BaseHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();

        switch (requestMethod){
            case "GET":
                handleGetRequest(exchange);
                break;
            case "POST":
                handlePostRequest(exchange);
                break;
            case "PUT":
                handlePutRequest(exchange);
                break;
            case "DELETE":
                handleDeleteRequest(exchange);
        }
    }

    public abstract void handleGetRequest(HttpExchange exchange) throws IOException;
    public abstract void handlePostRequest(HttpExchange exchange) throws IOException;
    public abstract void handlePutRequest(HttpExchange exchange) throws IOException;
    public abstract void handleDeleteRequest(HttpExchange exchange) throws IOException;
}
