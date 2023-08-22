package com.coherentsolutions.services.httpHandlers;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.dao.CategoryDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CategoryHandler extends BaseHandler{
    @Override
    public void handleGetRequest(HttpExchange exchange) throws IOException {
        // Get categories with products from database and serialize it to json
        List<Category> allCategoriesWithProducts = new ArrayList<>();
        var categoriesDao = new CategoryDao();
        var allDbCategories = categoriesDao.getAll();
        for (var category: allDbCategories) {
            var products = categoriesDao.GetAllCategoryProducts(category.getName());
            category.addProducts(products);
            allCategoriesWithProducts.add(category);
        }

        // Send a response
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(allCategoriesWithProducts);
        exchange.sendResponseHeaders(200, jsonString.length());
        OutputStream os = exchange.getResponseBody();
        os.write(jsonString.getBytes());
    }

    @Override
    public void handlePostRequest(HttpExchange exchange) throws IOException {
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
