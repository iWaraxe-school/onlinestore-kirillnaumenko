package com.coherentsolutions.commands;

import com.coherentsolutions.SortOptions;
import com.coherentsolutions.Store;
import com.coherentsolutions.XmlParser;
import com.coherentsolutions.categories.Category;
import com.coherentsolutions.comparators.ProductComparator;
import com.coherentsolutions.interfaces.ICommand;
import com.coherentsolutions.products.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortCommand implements ICommand {
    private Store store;
    public SortCommand(Store store){
        this.store = store;
    }

    @Override
    public void execute() {
        var sortingRules = XmlParser.ParseConfig();
        var allProducts = new ArrayList<Product>();

        for (var category: store.getCategories()) {
            var categoryProducts = ((Category<Product>)category).getProducts();
            allProducts.addAll(categoryProducts);
        }

//        var comparator = Comparator.comparing(Product:: getName)
//                .thenComparing(Product::getPrice);
//
//        if (sortingRules.get("name").equals(SortOptions.DESC)) comparator = comparator.reversed();
//
//        comparator = comparator.thenComparing(Product::getPrice);
//        if (sortingRules.get("price").equals(SortOptions.DESC)) {
//            comparator = comparator.thenComparing(Product::getPrice).reversed();
//        }

        //Collections.sort(allProducts, comparator);
        allProducts.sort(new ProductComparator(sortingRules));
        allProducts.forEach(System.out::println);
    }
}
