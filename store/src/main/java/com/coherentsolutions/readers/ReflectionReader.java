package com.coherentsolutions.readers;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.interfaces.ISourceReader;
import com.coherentsolutions.products.Product;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReflectionReader implements ISourceReader {
    @Override
    public <T extends Product> List<Category<T>> readCategories() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var categories = new ArrayList<Category<T>>();
        Reflections reflections = new Reflections("com.coherentsolutions.categories");
        Set<Class<? extends Category>> allClasses =
                reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> entity: allClasses) {
            var category = entity.getConstructor().newInstance();
            categories.add(category);
        }

        return categories;
    }
}
