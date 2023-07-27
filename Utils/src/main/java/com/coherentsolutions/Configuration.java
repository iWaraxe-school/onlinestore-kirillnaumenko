package com.coherentsolutions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Configuration {

    private static Configuration instance;
    public Map<String, SortOptions> sortOptions;
    public DataSource dataSource;
    public int productCount;
    public int threadCount;

    private Configuration(){
        sortOptions = XmlConfigPaser.GetSorting();
        dataSource = XmlConfigPaser.GetDataSource();
        productCount = XmlConfigPaser.GetProductCount();
        threadCount = XmlConfigPaser.GetThreadCount();
    }

    public static synchronized Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

}
