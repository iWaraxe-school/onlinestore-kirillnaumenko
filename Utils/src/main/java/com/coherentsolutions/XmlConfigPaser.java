package com.coherentsolutions;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.LinkedHashMap;
import java.util.Map;

public class XmlConfigPaser
{
    public static Map<String, SortOptions> GetSorting(){
        Map<String, SortOptions> map = new LinkedHashMap<>();
        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("consoleApp/src/main/resources/config.xml");
            var nodes = xml.getDocumentElement().getElementsByTagName("sort").item(0).getChildNodes();

            for (var i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i) instanceof Element) {
                    var key = nodes.item(i).getNodeName();
                    var value = SortOptions.valueOfIgnoreCase(nodes.item(i).getTextContent());

                    map.put(key, value);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }

    public static DataSource GetDataSource(){

        DataSource dataSource = null;
        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("consoleApp/src/main/resources/config.xml");
            var xmlDataSource = xml.getDocumentElement().getElementsByTagName("datasource").item(0).getTextContent();
            dataSource = DataSource.valueOfIgnoreCase(xmlDataSource);

        }catch (Exception e){
            e.printStackTrace();
        }

        return dataSource;
    }

    public static int GetProductCount(){

        int productCount = 0;
        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("consoleApp/src/main/resources/config.xml");
            var xmlProductCount = xml.getDocumentElement().getElementsByTagName("productcount").item(0).getTextContent();
            productCount = Integer.parseInt(xmlProductCount);

        }catch (Exception e){
            e.printStackTrace();
        }

        return productCount;
    }
}
