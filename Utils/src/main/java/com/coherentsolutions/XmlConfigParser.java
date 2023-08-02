package com.coherentsolutions;

import org.apache.log4j.LogManager;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class XmlConfigParser {
    private String configPath;
    public XmlConfigParser(String configPath){
        this.configPath = configPath;
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
}
