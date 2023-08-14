package com.coherentsolutions.tasks;

import com.coherentsolutions.AppLogger;
import com.coherentsolutions.ShoppingCart;

public class CartCleanerTask implements Runnable {
    private ShoppingCart cart;

    public CartCleanerTask(ShoppingCart cart){
        this.cart = cart;
    }

    @Override
    public void run() {
        AppLogger.getLogger().info("Clearing shopping cart task has been started...");
        System.out.println("Clearing shopping cart...");
        cart.clearShoppingCart();
    }
}
