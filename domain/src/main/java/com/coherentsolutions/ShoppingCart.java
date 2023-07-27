package com.coherentsolutions;

import com.coherentsolutions.products.Product;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ShoppingCart<T extends Product>{
    public ConcurrentLinkedQueue<T> products;

    public ShoppingCart(){
        this.products = new ConcurrentLinkedQueue<>();
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
        builder.append("Current products in cart:")
                .append("\r\n\t");
        for (var value : products) {
            builder.append(value.toString())
                    .append("\r\n\t");
        }
        return builder.toString();
    }
}
