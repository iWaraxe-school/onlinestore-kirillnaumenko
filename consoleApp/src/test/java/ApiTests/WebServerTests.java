package ApiTests;

import com.coherentsolutions.Randomizer;
import com.coherentsolutions.categories.Category;
import com.coherentsolutions.products.Product;
import com.coherentsolutions.services.RestAssuredClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class WebServerTests {

    private static RestAssured restClient;

    @BeforeAll
    public static void setup() {
        restClient = RestAssuredClient.getInstance().restClient;
    }

    @Test
    public void getProducts() throws JsonProcessingException {
        // Get products and verify it's not empty
        Response productsResponse = restClient.get("/products");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> productList = objectMapper.readValue(productsResponse.getBody().asString(), new TypeReference<List<Product>>() {});

        Assertions.assertTrue(productList.size() != 0);
    }

    @Test
    public void getCategories() throws JsonProcessingException {
        // Get categories with products and verify it's not empty
        Response categoriesResponse = restClient.get("/categories");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Category> categoriesList = objectMapper.readValue(categoriesResponse.getBody().asString(), new TypeReference<List<Category>>() {});

        for (var category: categoriesList) {
            Assertions.assertTrue(category.getProducts().size() != 0);
        }
    }

    @Test
    public void postProduct() throws JsonProcessingException {
        // Get products and select random
        Response productsResponse = restClient.get("/products");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> productList = objectMapper.readValue(productsResponse.getBody().asString(), new TypeReference<List<Product>>() {});
        var randomProduct = Randomizer.selectRandomElement(productList);
        String json = objectMapper.writeValueAsString(randomProduct);

        // Add selected product to cart
        Response response = restClient.given().body(json).post("/cart");

        // Verify response
        Assertions.assertTrue(response.getStatusCode() == 200);
    }

    @Test
    public void getCart() throws JsonProcessingException {
        // Get products and select random
        Response productsResponse = restClient.get("/products");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> productList = objectMapper.readValue(productsResponse.getBody().asString(), new TypeReference<List<Product>>() {});
        var randomProduct = Randomizer.selectRandomElement(productList);

        // Add selected product to cart
        String json = objectMapper.writeValueAsString(randomProduct);
        restClient.given().body(json).post("/cart");

        // Get cart and verify that cart has added product
        Response cartResponse = restClient.get("/cart");
        List<Product> productsInCart = objectMapper.readValue(cartResponse.getBody().asString(), new TypeReference<List<Product>>() {});

        Assertions.assertTrue(productsInCart.contains(randomProduct));
    }
}
