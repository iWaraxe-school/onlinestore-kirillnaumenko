package com.coherentsolutions;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AppLogger {
    private static final Logger logger = LogManager.getLogger(AppLogger.class);

    private AppLogger() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void initializeLogger() {
        BasicConfigurator.configure();
    }
}
