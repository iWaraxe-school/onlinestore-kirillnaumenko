package com.coherentsolutions.commands;

import com.coherentsolutions.Store;
import com.coherentsolutions.categories.Category;
import com.coherentsolutions.interfaces.ICommand;
import com.coherentsolutions.products.Product;

import java.util.ArrayList;
import java.util.Comparator;

public class TopCommand implements ICommand {
    private Store store;
    public TopCommand(Store store){
        this.store = store;
    }

    @Override
    public void execute() {
        var allProducts = new ArrayList<Product>();

        for (var category: store.getCategories()) {
            var categoryProducts = ((Category<Product>)category).getProducts();
            allProducts.addAll(categoryProducts);
        }

        var priceComparator = Comparator.comparing(Product::getPrice).reversed();
        allProducts.sort(priceComparator);
        allProducts.subList(0, 4).forEach(System.out::println);
    }
}
