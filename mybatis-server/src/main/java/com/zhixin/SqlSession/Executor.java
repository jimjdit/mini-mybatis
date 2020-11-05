package com.zhixin.SqlSession;


import com.zhixin.pojo.Configuration;
import com.zhixin.pojo.MapperStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName : Executor  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-06-20 08:42  //时间
 */
public interface Executor {

    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException, Exception;

}
