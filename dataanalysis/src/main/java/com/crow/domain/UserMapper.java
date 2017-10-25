package com.crow.domain;

import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/12.
 */

public interface UserMapper {

    @Insert("insert ignore into user (`name`,`gender`,`home_team`,`address`) values (#{name},#{gender},#{homeTeam},#{address})")
    void insert(User user);
    //获取用户的地域分布
    List<User> selectAllAddressesSort(Integer selectLimitNum);
    //获取用户的性别分布
    List<User> selectAllGender();
    //获取所有用户，并按照访问量排序
    List<User> selectAllUsersSortedByViews(Integer selectLimitNum);
}
