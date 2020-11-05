package com.zhixin.SqlSession;

/**
 * @ClassName : SqlSession  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-06-19 23:44  //时间
 */
public interface SqlSession {

    public <T> T getMapper(Class<?> mapperClass);
 }
