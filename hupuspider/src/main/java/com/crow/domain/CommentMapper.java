package com.crow.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by CrowHawk on 17/10/12.
 */
@Mapper
public interface CommentMapper {
    @Insert("insert into comment (`content`,`author`,`lit_num`,`title`) values(#{content},#{author},#{litNum},#{title})")
    void insert(Comment comment);
}
