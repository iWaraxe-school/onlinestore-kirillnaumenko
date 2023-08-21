package com.coherentsolutions.services;

import com.coherentsolutions.AppLogger;
import com.coherentsolutions.Configuration;
import com.coherentsolutions.Store;
import com.coherentsolutions.WebServerConnectionOptions;
import com.coherentsolutions.services.httpHandlers.CartHandler;
import com.coherentsolutions.services.httpHandlers.CategoryHandler;
import com.coherentsolutions.services.httpHandlers.ProductsHandler;
import java.net.InetSocketAddress;

public class HttpServer {

    private static com.sun.net.httpserver.HttpServer server;
    public static final String PORT = Configuration.getInstance().webserverConnection.get(WebServerConnectionOptions.PORT);
    public static final String URL = String.format("http://localhost:%s", PORT);

    public static void start(Store store){
        try {
            server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(Integer.parseInt(PORT)), 0);
            server.createContext("/products", new ProductsHandler()).setAuthenticator(new HttpAuthenticator());
            server.createContext("/categories", new CategoryHandler()).setAuthenticator(new HttpAuthenticator());
            server.createContext("/cart", new CartHandler(store)).setAuthenticator(new HttpAuthenticator());
            server.setExecutor(null);
            server.start();
            AppLogger.getLogger().info("Server started on :" + URL);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void stop(){
        if (server != null){
            server.stop(1);
            AppLogger.getLogger().info("Stopping web server");
        }
    }

}

