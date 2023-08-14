package com.coherentsolutions.dao;

import com.coherentsolutions.AppLogger;
import com.coherentsolutions.DatabaseConnector;
import com.coherentsolutions.categories.Category;
import com.coherentsolutions.products.Product;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao implements IProductDao<Category>{
    @Override
    public List<Category> getAll() {
        List<Category> allCategories = new ArrayList<>();
        try (Statement stmt = DatabaseConnector.createConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM [master].[dbo].[Categories]");
            while (rs.next()) {
                var category = new Category<>();
                category.setId(rs.getInt("Id"));
                category.setName(rs.getString("Name"));

                allCategories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCategories;
    }

    @Override
    public Optional<Category> get(int id) {
        return Optional.empty();
    }

    @Override
    public void create(Category category) {
        try (Connection connection = DatabaseConnector.createConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Categories (Name) VALUES (?)")) {

            statement.setString(1, category.getName());
            statement.executeUpdate();

        } catch (SQLServerException e) {
            AppLogger.getLogger().info("Inserted category already exists");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category product, String[] params) {

    }

    @Override
    public void delete(Category product) {

    }

    public List<Product> GetAllCategoryProducts(String categoryName){
        Category category = new Category();
        List<Product> allProducts = new ArrayList<>();
        try (Statement stmt = DatabaseConnector.createConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM [master].[dbo].[Products] WHERE [master].[dbo].[Products].CategoryName = '%s'", categoryName));
            while (rs.next()) {
                category.setId(rs.getInt("Id"));
                category.setName(rs.getString("Name"));
            }

            ProductDao productDao = new ProductDao();
            allProducts = productDao.getAll().stream().filter(l -> l.getCategoryName().equalsIgnoreCase(categoryName)).toList();

            return allProducts;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allProducts;
    }
}
