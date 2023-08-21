package com.coherentsolutions.services.httpHandlers;

import com.coherentsolutions.ShoppingCart;
import com.coherentsolutions.Store;
import com.coherentsolutions.products.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CartHandler extends BaseHandler {
    private Store store;

    public CartHandler(Store store){
        this.store = store;
    }
    @Override
    public void handleGetRequest(HttpExchange exchange) throws IOException {
        // Get shop cart and serialize it to json for response
        ShoppingCart cart = store.getCart();
        List<Product> products = cart.getProducts();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(products);
        exchange.sendResponseHeaders(200, jsonString.length());
        OutputStream os = exchange.getResponseBody();
        os.write(jsonString.getBytes());
    }

    @Override
    public void handlePostRequest(HttpExchange exchange) throws IOException {
        // Get request body, deserialize it to object and put in shop cart
        InputStream requestBody = exchange.getRequestBody();
        String productData = new String(requestBody.readAllBytes());
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(productData, new TypeReference<Product>() {});
        store.getCart().addProduct(product);

        // Send a response
        String responseMessage = "Product added to cart";
        exchange.sendResponseHeaders(200, responseMessage.getBytes().length);
        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(responseMessage.getBytes());
        responseBody.close();
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
