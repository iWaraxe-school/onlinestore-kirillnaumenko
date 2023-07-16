package com.coherentsolutions;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.commands.*;
import com.coherentsolutions.interfaces.ISourceReader;
import com.coherentsolutions.products.Product;
import com.coherentsolutions.readers.DatabaseReader;
import com.coherentsolutions.readers.HttpReader;
import com.coherentsolutions.readers.ReflectionReader;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class StoreHelper {
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

    public static void LaunchStore(Store store){
        var showCommand = new ShowProductsCommand(store);
        var sortCommand = new SortCommand(store);
        var topCommand = new TopCommand(store);
        var quitCommand = new QuitCommand();
        var defaultCommand = new DefaultCommand();
        System.out.println("Enter 'quit' for exit");
        System.out.println("Please enter your command:");
        var input = new Scanner(System.in);
        while (true) {
            var line = input.nextLine();
            switch (line){
                case "show" -> showCommand.execute();
                case "sort" -> sortCommand.execute();
                case "top" -> topCommand.execute();
                case "quit" -> quitCommand.execute();
                default -> defaultCommand.execute();
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
