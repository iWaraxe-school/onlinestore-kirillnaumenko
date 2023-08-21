package com.coherentsolutions.readers;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.interfaces.ISourceReader;
import com.coherentsolutions.services.RestAssuredClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import java.util.List;

public class HttpReader implements ISourceReader {
    @Override
    public List<Category> readCategories() throws JsonProcessingException {
        Response categoriesResponse = RestAssuredClient.getInstance().restClient.get("/categories");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Category> categoriesList = objectMapper.readValue(categoriesResponse.getBody().asString(), new TypeReference<List<Category>>() {});

        return  categoriesList;
    }
}
