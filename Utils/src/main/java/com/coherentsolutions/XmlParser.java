package com.coherentsolutions;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.LinkedHashMap;
import java.util.Map;

public class XmlParser
{
    public static Map<String, SortOptions> ParseConfig(){
        Map<String, SortOptions> map = new LinkedHashMap<>();
        try {
            var xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("consoleApp/src/main/resources/config.xml");
            var nodes = xml.getDocumentElement().getChildNodes();

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
}
