package com.crow.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProxyIpMapper {

    @Insert("insert into ip_pool (`ip`,`port`) values (#{ip},#{port})")
    void insert(ProxyIp proxyIp);

    @Select("select * from ip_pool where id = #{id}")
    public ProxyIp findProxyIpById(int id);

    @Select("select * from ip_pool")
    public List<ProxyIp> findAllProxies();
}