package com.coherentsolutions;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class XmlConfigParser {
    private String configPath;
    public XmlConfigParser(String configPath){
        this.configPath = configPath;
        this.validateConfig(this.configPath);
    }

    public Map<String, SortOptions> GetSorting(){
        Map<String, SortOptions> map = new LinkedHashMap<>();
        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(configPath);
            var nodes = xml.getDocumentElement().getElementsByTagName("sort").item(0).getChildNodes();

            for (var i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i) instanceof Element) {
                    var key = nodes.item(i).getNodeName();
                    var value = SortOptions.valueOfIgnoreCase(nodes.item(i).getTextContent());

                    map.put(key, value);
                }
            }
        }catch (ParserConfigurationException | IOException e){
            AppLogger.getLogger().error(e.toString());
        }catch (Exception e){
            AppLogger.getLogger().error(e.toString());
        }

        return map;
    }

    public Map<DatabaseConnectionsOptions, String> GetDatabaseConnection(){
        Map<DatabaseConnectionsOptions, String> map = new LinkedHashMap<>();
        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(configPath);
            var nodes = xml.getDocumentElement().getElementsByTagName("database").item(0).getChildNodes();

            for (var i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i) instanceof Element) {
                    var key = DatabaseConnectionsOptions.valueOfIgnoreCase(nodes.item(i).getNodeName());
                    var value = nodes.item(i).getTextContent();

                    map.put(key, value);
                }
            }
        }catch (ParserConfigurationException | IOException e){
            AppLogger.getLogger().error(e.toString());
        }catch (Exception e){
            AppLogger.getLogger().error(e.toString());
        }

        return map;
    }

    public Map<WebServerConnectionOptions, String> GetWebServerConnection(){
        Map<WebServerConnectionOptions, String> map = new LinkedHashMap<>();
        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(configPath);
            var nodes = xml.getDocumentElement().getElementsByTagName("webserver").item(0).getChildNodes();

            for (var i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i) instanceof Element) {
                    var key = WebServerConnectionOptions.valueOfIgnoreCase(nodes.item(i).getNodeName());
                    var value = nodes.item(i).getTextContent();

                    map.put(key, value);
                }
            }
        }catch (ParserConfigurationException | IOException e){
            AppLogger.getLogger().error(e.toString());
        }catch (Exception e){
            AppLogger.getLogger().error(e.toString());
        }

        return map;
    }

    public Optional<DataSource> GetDataSource() throws ParserConfigurationException {

        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(configPath);
            var xmlDataSource = xml.getDocumentElement().getElementsByTagName("datasource").item(0).getTextContent();

            return  Optional.ofNullable(DataSource.valueOfIgnoreCase(xmlDataSource));

        }catch (ParserConfigurationException | IOException e){
            AppLogger.getLogger().error(e.toString());
        }catch (Exception e){
            AppLogger.getLogger().error(e.toString());
        }

        return Optional.empty();
    }

    public int GetProductCount(){

        int productCount = 0;
        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(configPath);
            var xmlProductCount = xml.getDocumentElement().getElementsByTagName("productcount").item(0).getTextContent();
            productCount = Integer.parseInt(xmlProductCount);

        }catch (ParserConfigurationException | IOException e){
            AppLogger.getLogger().error(e.toString());
        }catch (Exception e){
            AppLogger.getLogger().error(e.toString());
        }

        return productCount;
    }

    public int GetThreadCount(){

        int threadCount = 0;
        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(configPath);
            var xmlProductCount = xml.getDocumentElement().getElementsByTagName("threadcount").item(0).getTextContent();
            threadCount = Integer.parseInt(xmlProductCount);

        }catch (ParserConfigurationException | IOException e){
            AppLogger.getLogger().error(e.toString());
        }catch (Exception e){
            AppLogger.getLogger().error(e.toString());
        }

        return threadCount;
    }

    private void validateConfig(String path){
        try {
            // Load XML Schema and file
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("consoleApp/src/main/resources/schema.xsd"));
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);

            // Validate XML Data against XSD
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(xml));

        } catch (SAXException | IOException e) {
            AppLogger.getLogger().error(e.toString());
        } catch (Exception e) {
            AppLogger.getLogger().error(e.toString());
        }
    }
}
