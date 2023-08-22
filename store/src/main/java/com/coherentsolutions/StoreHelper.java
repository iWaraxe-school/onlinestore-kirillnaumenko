package com.coherentsolutions;

import com.coherentsolutions.categories.BookCategory;
import com.coherentsolutions.categories.Category;
import com.coherentsolutions.categories.FoodCategory;
import com.coherentsolutions.dao.CategoryDao;
import com.coherentsolutions.dao.ProductDao;
import com.coherentsolutions.interfaces.ISourceReader;
import com.coherentsolutions.products.Product;
import com.coherentsolutions.readers.DatabaseReader;
import com.coherentsolutions.readers.HttpReader;
import com.coherentsolutions.readers.ReflectionReader;
import com.coherentsolutions.services.DatabaseService;
import com.coherentsolutions.services.HttpServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StoreHelper {
    private StoreHelper(){}
    public static void FillStoreWithCategories(Store store, DataSource dataSource) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, JsonProcessingException {
        ISourceReader source;
        List<Category<Product>> categories = new ArrayList<>();
        ProductGenerator generator = new ProductGenerator();
        switch (dataSource){
            case DATABASE:
                // Generate database and fill it with categories with random products
                DatabaseService.InitializeDatabase();
                var categoryDao = new CategoryDao();
                var productDao = new ProductDao();

                var bookCategory = new BookCategory();
                var books = generator.generateBook(5);
                categoryDao.create(bookCategory);
                for (var book: books) {
                    book.setCategoryName(bookCategory.getName());
                    productDao.create(book);
                }

                var foodCategory = new FoodCategory();
                var foods = generator.generateFood(5);
                categoryDao.create(foodCategory);
                for (var food: foods) {
                    food.setCategoryName(foodCategory.getName());
                    productDao.create(food);
                }

                source = new DatabaseReader();
                categories = source.readCategories();
                store.addCategories(categories);

                break;

            case HTTP:
                // Generate database, fill it with categories and start web server that get products from this database
                DatabaseService.InitializeDatabase();
                HttpServer.start(store);

                categoryDao = new CategoryDao();
                productDao = new ProductDao();

                bookCategory = new BookCategory();
                books = generator.generateBook(5);
                categoryDao.create(bookCategory);
                for (var book: books) {
                    book.setCategoryName(bookCategory.getName());
                    productDao.create(book);
                }

                foodCategory = new FoodCategory();
                foods = generator.generateFood(5);
                categoryDao.create(foodCategory);
                for (var food: foods) {
                    food.setCategoryName(foodCategory.getName());
                    productDao.create(food);
                }

                source = new HttpReader();
                categories = source.readCategories();
                store.addCategories(categories);

                break;

            default:
                source = new ReflectionReader();
                categories = source.readCategories();
                generator.generateProducts(categories);
                store.addCategories(categories);
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
