package com.zhixin.mapper;

import com.zhixin.domain.Address;

import java.util.List;

public interface IAddressMapper {


    public List<Address> selectList();

    public Address selectOne(Address address);

    public int insert(Address address);

    public int update(Address address);

    public int delete(Address address);

}
