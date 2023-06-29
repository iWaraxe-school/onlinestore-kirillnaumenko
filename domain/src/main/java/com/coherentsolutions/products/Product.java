package com.coherentsolutions.products;

public class Product {
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
}
