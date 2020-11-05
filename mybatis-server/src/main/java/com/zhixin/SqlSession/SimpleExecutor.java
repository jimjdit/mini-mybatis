package com.zhixin.SqlSession;


import com.zhixin.config.BoundSql;
import com.zhixin.pojo.Configuration;
import com.zhixin.pojo.MapperStatement;
import com.zhixin.utils.GenericTokenParser;
import com.zhixin.utils.ParameterMapping;
import com.zhixin.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : SimpleExecutor  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-06-20 08:44  //时间
 */
public class SimpleExecutor implements Executor {
    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... params) throws Exception {

        //注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();

        //获取sql
        String sql = mapperStatement.getStatement();
        BoundSql boundSql = getBoundSql(sql);

        //3获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        //4 设置参数

        //获取参数类型
        String paramType = mapperStatement.getParamType();

        if (paramType != null) {
            //反射获取类
            Class<?> aClass =  getClassType(paramType);
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
            for (int i = 0; i < parameterMappingList.size(); i++) {
                //ParameterMapping 属性名称
                ParameterMapping parameterMapping = parameterMappingList.get(i);
                String content = parameterMapping.getContent();
                //暴力反射获取参数值
                Field declaredField = aClass.getDeclaredField(content);
                declaredField.setAccessible(true);
                Object o = declaredField.get(params[0]);

                //封装sql参数
                preparedStatement.setObject(i + 1, o);
            }
        }

        //执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mapperStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);

        ArrayList<Object> objects = new ArrayList<Object>();

        //封装返回结果集
        while (resultSet.next()){
            Object o = resultTypeClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {

                //获取字段名
                String columnName = metaData.getColumnName(i);
                //获取字段值
                Object value = resultSet.getObject(columnName);

                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);

            }
            objects.add(o);
        }

        return ( List<E> )objects;
    }




    public int queryUpdate(Configuration configuration, MapperStatement mapperStatement, Object... params) throws Exception {

        //注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();

        //获取sql
        String sql = mapperStatement.getStatement();
        BoundSql boundSql = getBoundSql(sql);

        //3获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        //4 设置参数

        //获取参数类型
        String paramType = mapperStatement.getParamType();

        if (paramType != null) {
            //反射获取类
            Class<?> aClass =  getClassType(paramType);
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
            for (int i = 0; i < parameterMappingList.size(); i++) {
                //ParameterMapping 属性名称
                ParameterMapping parameterMapping = parameterMappingList.get(i);
                String content = parameterMapping.getContent();
                //暴力反射获取参数值
                Field declaredField = aClass.getDeclaredField(content);
                declaredField.setAccessible(true);
                Object o = declaredField.get(params[0]);

                //封装sql参数
                preparedStatement.setObject(i + 1, o);
            }
        }

        //执行sql
        int rest = preparedStatement.executeUpdate();
        return rest;
    }



    private Class<?> getClassType(String paramType) throws ClassNotFoundException {
        if (paramType != null){
            Class<?> aClass = Class.forName(paramType);
            return aClass;
        }
        return null;
    }

    private BoundSql getBoundSql(String sql) {

        //标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        //解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        //解析出来的参数名称
        List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parseSql , parameterMappings);
        return boundSql;
    }
}
