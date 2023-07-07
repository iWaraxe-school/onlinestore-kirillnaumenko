package com.coherentsolutions;

import com.coherentsolutions.commands.*;

import java.lang.reflect.InvocationTargetException;
import java.security.PublicKey;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Store store = new Store();
        StoreHelper.FillStoreWithCategories(store);
        StoreHelper.LaunchStore(store);
    }
}