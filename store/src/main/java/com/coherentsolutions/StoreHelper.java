package com.coherentsolutions;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.commands.*;
import com.coherentsolutions.products.Product;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class StoreHelper {
    public static void FillStoreWithCategories(Store store) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var categories = readAllCategories();
        var generator = new ProductGenerator();
        generator.generateProducts(categories);
        store.addCategories(categories);
    }

    public static void LaunchStore(Store store){
        System.out.println("Enter 'quit' for exit");
        System.out.println("Please enter your command:");
        var input = new Scanner(System.in);
        while (true) {
            var line = input.nextLine();
            switch (line){
                case "show" -> new ShowProductsCommand(store).execute();
                case "sort" -> new SortCommand(store).execute();
                case "top" -> new TopCommand(store).execute();
                case "quit" -> new QuitCommand().execute();
                default -> new DefaultCommand().execute();
            }
        }
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
