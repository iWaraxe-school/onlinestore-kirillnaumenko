package com.coherentsolutions;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.*;

public class DatabaseConnector {
    private static Connection connection;

    public static Connection createRootConnection() {
        try {
            var connectionMap = Configuration.getInstance().databaseConnection;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser(connectionMap.get(DatabaseConnectionsOptions.USER));
            ds.setPassword(connectionMap.get(DatabaseConnectionsOptions.PASSWORD));
            ds.setServerName(connectionMap.get(DatabaseConnectionsOptions.URL));
            ds.setPortNumber(1433);
            ds.setTrustServerCertificate(true);
            connection = ds.getConnection();

            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            AppLogger.getLogger().info(e.toString());
            throw new RuntimeException("Database connection error");
        }
    }

    public static Connection createConnection() {
        try {
            var connectionMap = Configuration.getInstance().databaseConnection;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setDatabaseName(connectionMap.get(DatabaseConnectionsOptions.DATABASE));
            ds.setUser(connectionMap.get(DatabaseConnectionsOptions.USER));
            ds.setPassword(connectionMap.get(DatabaseConnectionsOptions.PASSWORD));
            ds.setServerName(connectionMap.get(DatabaseConnectionsOptions.URL));
            ds.setPortNumber(1433);
            ds.setTrustServerCertificate(true);
            connection = ds.getConnection();

            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            AppLogger.getLogger().info(e.toString());
            throw new RuntimeException("Database connection error");
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                AppLogger.getLogger().info(e.toString());
            }
        }
    }
}
