package com.coherentsolutions.services.httpHandlers;

import com.coherentsolutions.dao.ProductDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class ProductsHandler extends BaseHandler {
    @Override
    public void handleGetRequest(HttpExchange exchange) throws IOException {
        // Get products from database and serialize it to json
        ProductDao productsDao = new ProductDao();
        var allProducts = productsDao.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(allProducts);

        // Send a response
        exchange.sendResponseHeaders(200, jsonString.length());
        OutputStream os = exchange.getResponseBody();
        os.write(jsonString.getBytes());
        os.close();
    }

    @Override
    public void handlePostRequest(HttpExchange exchange){
        // TODO something
    }

    @Override
    public void handlePutRequest(HttpExchange exchange){
        // TODO something
    }

    @Override
    public void handleDeleteRequest(HttpExchange exchange){
        // TODO something
    }
}
