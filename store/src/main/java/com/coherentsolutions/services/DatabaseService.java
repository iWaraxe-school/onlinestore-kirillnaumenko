package com.coherentsolutions.services;

import com.coherentsolutions.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService {
    public static void InitializeDatabase(){
        // Make sure you have docker compose binaries
        // Run runsql.zsh before database initializations
        CreateCategoriesTable();
        CreateProductsTable();
    }

    private static void CreateCategoriesTable(){
        try {
            String createTableQuery = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Categories') " +
                    "BEGIN " +
                    "CREATE TABLE Categories ( " +
                    "Id INT IDENTITY(1,1) PRIMARY KEY, " +
                    "Name VARCHAR(50) UNIQUE, " +
                    ") " +
                    "END";

            Connection connection = DatabaseConnector.createConnection();
            PreparedStatement statement = connection.prepareStatement(createTableQuery);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void CreateProductsTable(){
        try {

            String createTableQuery = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Products') " +
                    "BEGIN " +
                    "CREATE TABLE Products ( " +
                    "Id INT IDENTITY(1,1) PRIMARY KEY, " +
                    "CategoryName VARCHAR(50) FOREIGN KEY REFERENCES Categories(Name), " +
                    "Name VARCHAR(50), " +
                    "Price FLOAT, " +
                    "RATE FLOAT " +
                    ") " +
                    "END";

            Connection connection = DatabaseConnector.createConnection();
            PreparedStatement statement = connection.prepareStatement(createTableQuery);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
