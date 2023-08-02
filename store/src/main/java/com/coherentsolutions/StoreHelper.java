package com.coherentsolutions;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.interfaces.ISourceReader;
import com.coherentsolutions.products.Product;
import com.coherentsolutions.readers.DatabaseReader;
import com.coherentsolutions.readers.HttpReader;
import com.coherentsolutions.readers.ReflectionReader;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

public class StoreHelper {
    private StoreHelper(){}
    public static void FillStoreWithCategories(Store store, DataSource dataSource) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ISourceReader source;
        switch (dataSource){
            case DATABASE -> source = new DatabaseReader();
            case HTTP -> source = new HttpReader();
            default -> source = new ReflectionReader();
        }

        var categories = source.readCategories();
        var generator = new ProductGenerator();
        generator.generateProducts(categories);
        store.addCategories(categories);
    }

    private static <T extends Product> ArrayList<Category<T>> readAllCategories() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var categories = new ArrayList<Category<T>>();
        var reflections = new Reflections("com.coherentsolutions.categories");
        Set<Class<? extends Category>> allClasses =
                reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> entity: allClasses) {
            var category = entity.getConstructor().newInstance();
            categories.add(category);
        }

        return categories;
    }
}
