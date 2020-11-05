package com.zhixin.io;

import java.io.InputStream;

/**
 * @program: mini-mybatis
 * @description: 读取文件信息
 * @author: zhi xin
 * @create: 2020-11-05 10:51
 */
public class Resource {

    /**
     * 利用类引导反射获取文件流
     * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path){
        InputStream resourceAsStream = Resource.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
