package com.sxx.configuration;

import com.sxx.model.Function;
import com.sxx.model.MappeXmlBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: sxx
 * @Date 2020/7/8 19:11
 * @Version 1.0
 **/
public class MyConfiguration {

    private ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    public Connection build(String resource){
        InputStream stream = classLoader.getResourceAsStream(resource);

        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(stream);
            Element element = document.getRootElement();
            try {
                return getConnection(element);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection(Element root) throws SQLException {
        Connection connection = null;
        List<Element> list = root.elements("property");
        String driverClassName = "",url = "",username = "",password = "";
        for (Element item : list){
            String name = item.attributeValue("name");
            switch (name){
                case "driverClassName" : driverClassName = item.getText();break;
                case "url" : url = item.getText();break;
                case "username" : username = item.getText();break;
                case "password" : password = item.getText();break;
            }
        }
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public MappeXmlBean parseMapperXml(String path){
        MappeXmlBean mappeXmlBean = new MappeXmlBean();
        try {
            InputStream stream = classLoader.getResourceAsStream(path);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(stream);
            Element element = document.getRootElement();
            mappeXmlBean.setInterfaceName(element.attributeValue("namespace").trim());
            List<Function> methods = new ArrayList<>();
            for (Iterator iterator = element.elementIterator(); iterator.hasNext();){
                Function function = new Function();
                Element el = (Element) iterator.next();
                function.setId(el.attributeValue("id").trim());
                function.setSql(el.getText());
                methods.add(function);
            }
            mappeXmlBean.setMethods(methods);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mappeXmlBean;
    }

    public String getValue(Element node){
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }
}
