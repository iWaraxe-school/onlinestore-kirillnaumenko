package com.coherentsolutions.readers;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.dao.CategoryDao;
import com.coherentsolutions.interfaces.ISourceReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseReader implements ISourceReader {
    @Override
    public List<Category> readCategories() {
        List<Category> allCategoriesWithProducts = new ArrayList<>();
        var categoriesDao = new CategoryDao();
        var allDbCategories = categoriesDao.getAll();
        for (var category: allDbCategories) {
            var products = categoriesDao.GetAllCategoryProducts(category.getName());
            category.addProducts(products);
            allCategoriesWithProducts.add(category);
        }

        return allCategoriesWithProducts;
    }
}
