package com.coherentsolutions;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.interfaces.IProductGenerator;
import com.coherentsolutions.products.Book;
import com.coherentsolutions.products.Food;
import com.coherentsolutions.products.Product;
import com.coherentsolutions.products.builders.BookBuilder;
import com.coherentsolutions.products.builders.FoodBuilder;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class ProductGenerator implements IProductGenerator{
    private Faker faker;
    private Configuration configuration;

    public ProductGenerator()
    {
        faker = new Faker();
        configuration = Configuration.getInstance();
    }

    @Override
    public <T extends Product> List<Category<T>> generateProducts(List<Category<T>> categories){
        for (Category category: categories) {
            var categoryName = category.getClass().getSimpleName();
            switch (categoryName) {
                case "BookCategory" -> category.addProducts(generateBook(configuration.productCount));
                case "FoodCategory" -> category.addProducts(generateFood(configuration.productCount));
                default -> category.addProducts(generateRandomProduct(configuration.productCount));
            }
        }
            return categories;
    }

    public List<Food> generateFood(int count){
        ArrayList<Food> products = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            var product = new FoodBuilder()
                    .setName(faker.food().fruit())
                    .setProducer(faker.company().name())
                    .setPrice(faker.number().randomDouble(2, 1, 1000))
                    .setRate(faker.number().randomDouble(1, 0, 5))
                    .build();

            products.add(product);
        }

        return products;
    }

    public List<Book> generateBook(int count){
        ArrayList<Book> products = new ArrayList<Book>();

        for (int i = 0; i < count; i++) {
            var product = new BookBuilder()
                    .setName(faker.book().title())
                    .setAuthor(faker.book().author())
                    .setPrice(faker.number().randomDouble(2, 1, 1000))
                    .setRate(faker.number().randomDouble(1, 0, 5))
                    .build();

            products.add(product);
        }

        return products;
    }

    private List<Product> generateRandomProduct(int count){
        Faker faker = new Faker();
        ArrayList<Product> products = new ArrayList<Product>();

        for (int i = 0; i < count; i++) {
            Product product = new Product();
            product.setName(faker.chuckNorris().fact());
            product.setRate(faker.number().randomDouble(1, 0, 5));
            product.setPrice(faker.number().randomDouble(2, 1, 1000));

            products.add(product);
        }

        return products;
    }
}
