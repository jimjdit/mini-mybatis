package com.zhixin.config;


import com.zhixin.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : BoundSql  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-06-20 09:28  //时间
 */
public class BoundSql {

    //解析后的sql
    private String sqlText;

    private List<ParameterMapping> parameterMappingList = new ArrayList<ParameterMapping>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
