package com.zhixin.SqlSession;


import com.zhixin.pojo.Configuration;
import com.zhixin.pojo.MapperStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @ClassName : DefaultSqlSession  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-06-19 23:44  //时间
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> selectList(String statemented, Object... params) throws Exception {

        //将要用完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statemented);
        List<Object> list = simpleExecutor.query(configuration, mapperStatement, params);
        return (List<E>)list;
    }

    public <T> T selectOne(String statemented, Object... params) throws Exception {

        List<Object> objects = selectList(statemented , params);

        if (objects.size() == 1){
            return (T)objects.get(0);
        }else {
            throw new RuntimeException("查询结果为空或者放回结果过多");
        }
    }


    public Integer insert(String statemented, Object... params) throws Exception {

        //将要用完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        Integer rest = simpleExecutor.queryUpdate(configuration, configuration.getMapperStatementMap().get(statemented), params);

        if (rest == 1){
            return rest;
        }else {
            throw new RuntimeException("新增失败");
        }
    }


    public Integer update(String statemented, Object... params) throws Exception {

        //将要用完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        Integer rest = simpleExecutor.queryUpdate(configuration, configuration.getMapperStatementMap().get(statemented), params);

        if (rest == 1){
            return rest;
        }else {
            throw new RuntimeException("新增失败");
        }
    }


    public <T> T getMapper(Class<?> mapperClass) {

        //使用jdk 动态代理，为dao接口生成代理对象并返回
       Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //方法名
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." + methodName;
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType){
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                }else if (genericReturnType instanceof GenericArrayType) {
                    return selectOne(statementId, args);
                }else{
                    return update(statementId , args);
                }
            }
        });

        return (T)proxyInstance;
    }
}
