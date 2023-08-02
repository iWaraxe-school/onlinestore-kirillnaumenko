package com.coherentsolutions.tasks;

import com.coherentsolutions.Randomizer;
import com.coherentsolutions.ShoppingCart;
import com.coherentsolutions.products.Product;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateOrderTask implements Runnable{
    private ShoppingCart cart;
    private List<Product> products;

    public CreateOrderTask(ShoppingCart cart, List<Product> products){
        this.cart = cart;
        this.products = products;
    }

    @Override
    public void run() {
        var randomProduct = Randomizer.selectRandomElement(products);
        cart.addProduct(randomProduct);

        System.out.println(MessageFormat.format("Adding {0} to shopping cart...", randomProduct.getName()));
        System.out.println(cart.toString());
    }
}
