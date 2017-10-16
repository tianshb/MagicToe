package com.crow.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by CrowHawk on 17/10/12.
 */
@Mapper
public interface PostMapper {
    @Insert("insert into post (`title`,`author`,`reply_num`) values (#{title},#{author},#{replyNum})")
    void insert(Post post);
}
