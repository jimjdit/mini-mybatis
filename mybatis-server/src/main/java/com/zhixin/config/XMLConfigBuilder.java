package com.zhixin.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zhixin.io.Resource;
import com.zhixin.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @program: mini-mybatis
 * @description: 解析配置文件
 * @author: zhi xin
 * @create: 2020-11-05 10:50
 */
public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();

        for (Element element: list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name , value);
        }

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);

        //解析mapper.xml 解析mapper获取路径
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element mapper : mapperList) {
            String path = mapper.attributeValue("resource");
            InputStream mapperInputStream = Resource.getResourceAsStream(path);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            configuration = xmlMapperBuilder.parseMapper(mapperInputStream);

        }


        return configuration;
    }

}
