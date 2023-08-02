package com.coherentsolutions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ShoppingCart<Product>{
    public List<Product> products;

    public ShoppingCart(){
        this.products = new ArrayList<Product>();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
    public void addProducts(List<Product> products){
        this.products.addAll(products);
    }
    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Current products in cart:")
                .append("\r\n\t");
        for (var value : products) {
            builder.append(value.toString())
                    .append("\r\n\t");
        }
        return builder.toString();
    }
}
