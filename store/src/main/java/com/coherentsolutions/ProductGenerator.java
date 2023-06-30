package com.coherentsolutions;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.interfaces.IProductGenerator;
import com.coherentsolutions.products.Book;
import com.coherentsolutions.products.Food;
import com.coherentsolutions.products.Product;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class ProductGenerator implements IProductGenerator{

    @Override
    public <T extends Product> List<Category<T>> generateProducts(List<Category<T>> categories){
        for (Category category: categories) {
            var categoryName = category.getClass().getSimpleName();
            var count = 5;
            switch (categoryName) {
                case "BookCategory" -> category.addProducts(generateBook(count));
                case "FoodCategory" -> category.addProducts(generateFood(count));
                default -> category.addProducts(generateRandomProduct(count));
            }
        }
            return categories;
    }

    private List<Food> generateFood(int count){
        Faker faker = new Faker();
        ArrayList<Food> products = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Food product = new Food();
            product.setName(faker.food().fruit());
            product.setProducer(faker.company().name());
            product.setRate(faker.number().randomDouble(1, 0, 5));
            product.setPrice(faker.number().randomDouble(2, 1, 1000));

            products.add(product);
        }

        return products;
    }

    private List<Book> generateBook(int count){
        Faker faker = new Faker();
        ArrayList<Book> products = new ArrayList<Book>();

        for (int i = 0; i < count; i++) {
            Book product = new Book();
            product.setName(faker.book().title());
            product.setAuthor(faker.book().author());
            product.setRate(faker.number().randomDouble(1, 0, 5));
            product.setPrice(faker.number().randomDouble(2, 1, 1000));

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
