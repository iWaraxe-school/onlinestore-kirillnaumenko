package com.coherentsolutions.tasks;

import com.coherentsolutions.ShoppingCart;

import java.util.concurrent.TimeUnit;

public class CartCleanerTask implements Runnable {
    private ShoppingCart cart;

    public CartCleanerTask(ShoppingCart cart){
        this.cart = cart;
    }

    @Override
    public void run() {
        System.out.println("Clearing shopping cart...");
        cart.products.clear();
    }
}
