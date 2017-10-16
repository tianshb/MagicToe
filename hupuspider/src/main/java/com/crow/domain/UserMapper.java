package com.crow.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by CrowHawk on 17/10/12.
 */
@Mapper
public interface UserMapper {
    @Insert("insert ignore into user (`name`,`gender`,`home_team`,`address`) values (#{name},#{gender},#{homeTeam},#{address})")
    void insert(User user);
}
