package com.coherentsolutions.products.builders;

import com.coherentsolutions.products.Food;

public class FoodBuilder {
    private Food food;

    public FoodBuilder(){
        this.food = new Food();
    }

    public FoodBuilder setProducer(String producer){
        this.food.setProducer(producer);

        return this;
    }

    public FoodBuilder setName(String name){
        this.food.setName(name);

        return this;
    }

    public FoodBuilder setPrice(Double price){
        this.food.setPrice(price);

        return this;
    }

    public FoodBuilder setRate(Double rate){
        this.food.setRate(rate);

        return this;
    }

    public Food build(){
        return this.food;
    }
}
