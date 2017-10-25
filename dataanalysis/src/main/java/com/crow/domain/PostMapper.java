package com.crow.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/12.
 */

public interface PostMapper {
    @Insert("insert ignore into post (`title`,`author`,`reply_num`) values (#{title},#{author},#{replyNum})")
    void insert(Post post);

    @Results(id = "postResult", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "title", column = "title"),
            @Result(property = "author", column = "author"),
            @Result(property = "replyNum", column = "reply_num")
    })
    @Select("select * from post order by reply_num desc limit #{selectLimitNum}")
    List<Post> selectAllPostsSorted(Integer selectLimitNum);
}

