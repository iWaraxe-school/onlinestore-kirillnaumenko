package com.coherentsolutions.comparators;

import com.coherentsolutions.SortOptions;
import com.coherentsolutions.products.Product;

import java.util.*;

public class ProductComparator implements Comparator<Product> {
    private Map<String, SortOptions> sortMap;

    public Map<String, SortOptions> getSortMap() {
        return sortMap;
    }

    public void setSortMap(LinkedHashMap<String, SortOptions> sortMap) {
        this.sortMap = sortMap;
    }

    public ProductComparator(Map<String, SortOptions> sortMap){
        this.sortMap = sortMap;
    }

    @Override
    public int compare(Product o1, Product o2) {
        var nameComparator = Comparator.comparing(Product::getName);
        var priceComparator = Comparator.comparing(Product::getPrice);
        var rateComparator = Comparator.comparing(Product::getRate);

        if (sortMap.get("name") == SortOptions.DESC) {
            nameComparator = nameComparator.reversed();
        }
        if (sortMap.get("price") == SortOptions.DESC) {
            priceComparator = priceComparator.reversed();
        }
        if (sortMap.get("rate") == SortOptions.DESC) {
            rateComparator = rateComparator.reversed();
        }

        var comparator = nameComparator
                .thenComparing(priceComparator)
                .thenComparing(rateComparator);

        return comparator.compare(o1, o2);
    }
}
