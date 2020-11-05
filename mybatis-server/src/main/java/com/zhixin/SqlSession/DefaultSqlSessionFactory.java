package com.zhixin.SqlSession;

import com.zhixin.pojo.Configuration;

/**
 * @ClassName : DefaultSqlSessionFactory  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-06-19 23:34  //时间
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
