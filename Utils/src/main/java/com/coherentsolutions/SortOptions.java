package com.coherentsolutions;

public enum SortOptions {
    ASC,
    DESC;

    public static SortOptions valueOfIgnoreCase(String name) {
        return valueOf(name.toUpperCase());
    }
}
