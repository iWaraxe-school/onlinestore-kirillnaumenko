package com.coherentsolutions.dao;

import com.coherentsolutions.DatabaseConnector;
import com.coherentsolutions.products.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao implements IProductDao<Product>{
    @Override
    public List<Product> getAll() {
        List<Product> allProducts = new ArrayList<>();
        try (Statement stmt = DatabaseConnector.createConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM [master].[dbo].[Products]");
            while (rs.next()) {
                var product = new Product();
                product.setId(rs.getInt("Id"));
                product.setCategoryName(rs.getString("CategoryName"));
                product.setName(rs.getString("Name"));
                product.setPrice(Double.valueOf(String.format("%.2f", rs.getFloat("Price"))));
                product.setRate(Double.valueOf(String.format("%.2f", rs.getFloat("Rate"))));

                allProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    @Override
    public Optional<Product> get(int id) {
        return Optional.empty();
    }

    @Override
    public void create(Product product) {
        try (Connection connection = DatabaseConnector.createConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Products (CategoryName, Name, Price, Rate) VALUES (?, ?, ?, ?)")) {

            statement.setString(1, product.getCategoryName());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setDouble(4, product.getRate());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product, String[] params) {

    }

    @Override
    public void delete(Product product) {

    }
}
