package com.coherentsolutions.commands;

import com.coherentsolutions.AppLogger;
import com.coherentsolutions.Store;
import com.coherentsolutions.ThreadPoolManager;
import com.coherentsolutions.interfaces.ICommand;
import com.coherentsolutions.tasks.CartCleanerTask;
import com.coherentsolutions.tasks.CreateOrderTask;

import java.util.concurrent.*;

public class OrderCommand implements ICommand {
    private Store store;
    public OrderCommand(Store store){
        this.store = store;
    }
    @Override
    public void execute() {
        AppLogger.getLogger().info("Initialize two background processes for product ordering...");
        var allProducts = store.GetAllProducts();

        ScheduledExecutorService threadPool = ThreadPoolManager.scheduledThreadPool;

        threadPool.scheduleAtFixedRate(new CreateOrderTask(store.getCart(), allProducts), 0, 5, TimeUnit.SECONDS);
        threadPool.scheduleAtFixedRate(new CartCleanerTask(store.getCart()), 30, 30, TimeUnit.SECONDS);
    }
}
