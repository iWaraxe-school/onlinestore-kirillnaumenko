package com.coherentsolutions;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.reflect.InvocationTargetException;

public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, JsonProcessingException {
        AppLogger.initializeLogger();
        Store store = new Store();
        StoreHelper.FillStoreWithCategories(store, Configuration.getInstance().dataSource);
        StoreInteraction.LaunchStore(store);
    }
}
