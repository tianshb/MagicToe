package com.crow.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by CrowHawk on 17/10/6.
 */
@Mapper
public interface PostInfoMapper {

    //#{title}和#{author}对应HupuBxjPostInfo对象的title和author属性
    @Insert("insert into PostInfo (`title`,`author`,`text`) values (#{title},#{author},#{text})")
    int add(HupuBxjPostInfo hupuBxjPostInfo);
}
