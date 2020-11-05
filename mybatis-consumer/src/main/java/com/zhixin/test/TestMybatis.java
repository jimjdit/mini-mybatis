package com.zhixin.test;

import com.zhixin.SqlSession.SqlSession;
import com.zhixin.SqlSession.SqlSessionFactory;
import com.zhixin.SqlSession.SqlSessionFactoryBuilder;
import com.zhixin.domain.Address;
import com.zhixin.io.Resource;
import com.zhixin.mapper.IAddressMapper;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : TestMybatis  //类名
 * @Description :   //描述
 * @Author : HuangZhiXin //作者
 * @Date: 2020-06-21 16:16  //时间
 */
public class TestMybatis {

    private SqlSession sqlSession;
    @Before
    public void getSqlSession() throws PropertyVetoException, DocumentException {
        InputStream resourceAsStream = Resource.getResourceAsStream("config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
         sqlSession = sqlSessionFactory.openSession();
    }


    @Test
    public void selectList() throws PropertyVetoException, DocumentException {
        IAddressMapper mapper = sqlSession.getMapper(IAddressMapper.class);
        List<Address> addressList = mapper.selectList();
        for (Address address : addressList) {
            System.out.println(address.toString());
        }
    }


    @Test
    public void selectOne(){
        IAddressMapper mapper = sqlSession.getMapper(IAddressMapper.class);
        Address address = new Address();
        address.setId(1);
        address.setProvince("广东");
        Address address1 = mapper.selectOne(address);
        System.out.println(address1.toString());

    }

    @Test
    public void insert(){
        IAddressMapper mapper = sqlSession.getMapper(IAddressMapper.class);
        Address address = new Address();
        address.setId(4);
        address.setUserId(1);
        address.setProvince("广东");
        address.setCity("揭阳");
        int rest = mapper.insert(address);
        System.out.println(rest);

    }



    @Test
    public void update(){
        IAddressMapper mapper = sqlSession.getMapper(IAddressMapper.class);
        Address address = new Address();
        address.setId(3);
        address.setUserId(1);
        address.setProvince("广西");
        int rest = mapper.update(address);
        System.out.println(rest);

    }

    @Test
    public void delete(){
        IAddressMapper mapper = sqlSession.getMapper(IAddressMapper.class);
        Address address = new Address();
        address.setId(3);
        int rest = mapper.delete(address);
        System.out.println(rest);

    }



}
