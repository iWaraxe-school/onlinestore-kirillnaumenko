package com.coherentsolutions.categories;

import com.coherentsolutions.products.Product;
import java.util.ArrayList;
import java.util.List;

public abstract class Category <T extends Product> {
    private String name;
    private List<T> products;

    public Category(){
        this.products = new ArrayList<>();
    }

    public List<T> getProducts() {
        return products;
    }

    public void setProducts(List<T> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(T product){
        this.products.add(product);
    }
    public void addProducts(List<T> products){
        this.products.addAll(products);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Category: ").append(name)
                .append("\r\n\t");
        for (var value : products) {
            builder.append(value.toString())
                    .append("\r\n\t");
        }
        return builder.toString();
    }
}
