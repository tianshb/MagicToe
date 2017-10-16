package com.crow.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProxyIpMapper {

    @Insert("insert into ip_pool (`ip`,`port`) values (#{ip},#{port})")
    void insert(ProxyIp proxyIp);
}