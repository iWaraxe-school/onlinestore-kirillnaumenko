package com.coherentsolutions.commands;

import com.coherentsolutions.Configuration;
import com.coherentsolutions.Store;
import com.coherentsolutions.comparators.ProductComparator;
import com.coherentsolutions.interfaces.ICommand;

public class SortCommand implements ICommand {
    private Store store;
    public SortCommand(Store store){
        this.store = store;
    }

    @Override
    public void execute() {
        var sortingRules = Configuration.getInstance().sortOptions;
        var allProducts = store.GetAllProducts();

        allProducts.sort(new ProductComparator(sortingRules));
        allProducts.forEach(System.out::println);
    }
}
