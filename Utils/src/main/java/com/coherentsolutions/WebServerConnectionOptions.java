package com.coherentsolutions;

public enum WebServerConnectionOptions {
    URL,
    PORT,
    USER,
    PASSWORD;

    public static WebServerConnectionOptions valueOfIgnoreCase(String name) {
        return valueOf(name.toUpperCase());
    }
}
