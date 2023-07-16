package com.coherentsolutions;

public enum DataSource {
    REFLECTION,
    DATABASE,
    HTTP;

    public static DataSource valueOfIgnoreCase(String name) {
        return valueOf(name.toUpperCase());
    }
}
