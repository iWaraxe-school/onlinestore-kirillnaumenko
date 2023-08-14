package com.coherentsolutions;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.products.Product;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;

public class Store <T extends Product> {
    private String name;
    private ShoppingCart cart;
    private List<Category<T>> categories;

    public Store(){
        name = new Faker().company().name();
        categories = new ArrayList<>();
        cart = new ShoppingCart();
    }

    public List<Category<T>> getCategories() {
        return categories;
    }
    public void setCategories(List<Category<T>> categories) {
        this.categories = categories;
    }
    public void addCategory(Category<T> category) {
        this.categories.add(category);
    }
    public void addCategories(List<Category<T>> categories) {
        this.categories.addAll(categories);
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> GetAllProducts(){
        var allProducts = new ArrayList<Product>();

        for (var category: getCategories()) {
            var categoryProducts = category.getProducts();
            allProducts.addAll(categoryProducts);
        }

        return allProducts;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Store ").append("'" + name + "'")
                .append("\r\n");
        for (var value : categories) {
            builder.append(value.toString())
                    .append("\r\n");
        }

        return builder.toString();
    }
}


