package com.zhixin.SqlSession;

import com.zhixin.config.XMLConfigBuilder;
import com.zhixin.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @ClassName : SqlSessionFactory  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-06-19 21:08  //时间
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws PropertyVetoException, DocumentException {

        XMLConfigBuilder xmlConfig = new XMLConfigBuilder();
        Configuration configuration = xmlConfig.parseConfig(in);

        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return defaultSqlSessionFactory;
    }


}
