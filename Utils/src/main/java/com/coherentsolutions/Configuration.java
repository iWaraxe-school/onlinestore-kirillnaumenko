package com.coherentsolutions;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.Map;

public class Configuration {
    public String configPath;
    public Map<String, SortOptions> sortOptions;
    public Map<DatabaseConnectionsOptions, String> databaseConnection;
    public Map<WebServerConnectionOptions, String> webserverConnection;
    public DataSource dataSource;
    public int productCount;
    public int threadCount;
    public XmlConfigParser parser;

    private Configuration() throws ParserConfigurationException {
        configPath = LoadConfiguration();
        parser = new XmlConfigParser(configPath);
        sortOptions = parser.GetSorting();
        databaseConnection = parser.GetDatabaseConnection();
        webserverConnection = parser.GetWebServerConnection();
        dataSource = parser.GetDataSource().get();
        productCount = parser.GetProductCount();
        threadCount = parser.GetThreadCount();
    }

    // Since Java class loader is thread safe, this code should provide lazy initialization and thread safe config loader
    private static class ConfigurationV2Loader {
        private static final Configuration INSTANCE;

        static {
            try {
                INSTANCE = new Configuration();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Configuration getInstance() {

        return ConfigurationV2Loader.INSTANCE;
    }

    private String LoadConfiguration(){
        // Pass required config file using IC/CD or CLI command
        // java -Dconfig.file={path to config}
        String configFileLocation = System.getProperty("config.file");

        if (configFileLocation == null) {
            System.out.println("There is no provided configuration file. Using default path.");
            configFileLocation = "consoleApp/src/main/resources/dev.config.xml";
        }

        File configFile = new File(configFileLocation);

        if (!configFile.exists()) {
            System.err.println("Config file does not exist.");
            System.exit(1);
        }

        System.out.println("Loaded config:" + configFile.getAbsolutePath());

        return configFileLocation;
    }
}
