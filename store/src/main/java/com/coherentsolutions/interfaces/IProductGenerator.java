package com.coherentsolutions.interfaces;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.products.Product;

import java.util.List;

public interface  IProductGenerator {
    <T extends Product> List<Category<T>> generateProducts(List<Category<T>> categories);
}
