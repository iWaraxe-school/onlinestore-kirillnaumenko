package com.coherentsolutions.interfaces;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.products.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ISourceReader {
    public <T extends Product> List<Category<T>> readCategories() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JsonProcessingException;
}
