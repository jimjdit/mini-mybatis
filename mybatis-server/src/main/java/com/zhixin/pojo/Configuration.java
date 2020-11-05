package com.zhixin.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : Configuration  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-11-05 20:27  //时间
 */
public class Configuration {

    private DataSource dataSource;

    private Map<String , MapperStatement> mapperStatementMap = new HashMap<String, MapperStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperStatement> getMapperStatementMap() {
        return mapperStatementMap;
    }

    public void setMapperStatementMap(Map<String, MapperStatement> mapperStatementMap) {
        this.mapperStatementMap = mapperStatementMap;
    }
}
