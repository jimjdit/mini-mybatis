package com.zhixin.pojo;

/**
 * @ClassName : MapperStatement  //类名
 * @Description :   sql 映射配置信息//描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-11-05 11:00  //时间
 */
public class MapperStatement {

    private String id;

    private String nameSpace;

    private String resultType;

    private String paramType;

    private String statement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
