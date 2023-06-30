package com.coherentsolutions;

import java.lang.reflect.InvocationTargetException;

public class StoreApp {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Store store = new Store();
        StoreHelper.FillStoreWithCategories(store);
        System.out.println(store);
    }
}
