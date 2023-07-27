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
        while (true) {
            try {
                System.out.println("Clearing shopping cart...");
                TimeUnit.SECONDS.sleep(60);
                cart.products.clear();
            } catch (InterruptedException ignored) {

            }
        }
    }
}
