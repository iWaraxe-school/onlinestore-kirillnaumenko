package com.coherentsolutions;

public enum DatabaseConnectionsOptions {
    URL,
    USER,
    PASSWORD,
    DATABASE;

    public static DatabaseConnectionsOptions valueOfIgnoreCase(String name) {
        return valueOf(name.toUpperCase());
    }
}
