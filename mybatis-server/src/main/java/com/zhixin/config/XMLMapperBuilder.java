package com.zhixin.config;

import com.zhixin.pojo.Configuration;
import com.zhixin.pojo.MapperStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : XMLMapperBuilder  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-06-19 22:33  //时间
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration parseMapper(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");

        List<Element> elements = rootElement.selectNodes("select|insert|update|delete");
        for (Element element : elements) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramType = element.attributeValue("paramType");
            String statement = element.getTextTrim();
            MapperStatement mapperStatement = new MapperStatement();
            mapperStatement.setId(id);
            mapperStatement.setNameSpace(namespace);
            mapperStatement.setParamType(paramType);
            mapperStatement.setResultType(resultType);
            mapperStatement.setStatement(statement);
            String key = namespace + "." + id;
            configuration.getMapperStatementMap().put(key , mapperStatement);
        }
        return configuration;
    }
}
