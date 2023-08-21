package com.coherentsolutions.products;

import java.util.Objects;

public class Product {

    private int id;
    private String categoryName;
    private String name;
    private Double rate;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Product: " + name)
                .append("\r\n\t\t")
                .append("Rate:" + rate)
                .append("\r\n\t\t")
                .append("Price: "+ price)
                .append("\r\n\t");
        return builder.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return
                Objects.equals(categoryName, product.categoryName) &&
                Objects.equals(name, product.name) &&
                Objects.equals(rate, product.rate) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, name, rate, price);
    }
}
