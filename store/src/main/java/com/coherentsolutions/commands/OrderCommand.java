package com.coherentsolutions.commands;

import com.coherentsolutions.Configuration;
import com.coherentsolutions.Store;
import com.coherentsolutions.categories.Category;
import com.coherentsolutions.interfaces.ICommand;
import com.coherentsolutions.products.Product;
import com.coherentsolutions.tasks.CartCleanerTask;
import com.coherentsolutions.tasks.CreateOrderTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class OrderCommand implements ICommand {
    private Store store;
    public OrderCommand(Store store){
        this.store = store;
    }
    @Override
    public void execute() {
        var allProducts = new ArrayList<Product>();

        for (var category: store.getCategories()) {
            var categoryProducts = ((Category<Product>)category).getProducts();
            allProducts.addAll(categoryProducts);
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(Configuration.getInstance().threadCount,
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = Executors.defaultThreadFactory().newThread(r);
                        t.setDaemon(true);
                        return t;
                    }
                });

        threadPool.submit(new CreateOrderTask(store.getCart(), allProducts));
        threadPool.submit(new CartCleanerTask(store.getCart()));
    }
}
