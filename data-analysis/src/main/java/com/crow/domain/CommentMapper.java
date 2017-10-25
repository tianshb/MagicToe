package com.crow.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/12.
 */

public interface CommentMapper {
    @Insert("insert into comment (`content`,`author`,`lit_num`,`title`) values(#{content},#{author},#{litNum},#{title})")
    void insert(Comment comment);

    @Results(id = "commentResult", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "litNum", column = "lit_num"),
            @Result(property = "author", column = "author"),
            @Result(property = "content", column = "content"),
            @Result(property = "title", column = "title")
    })
    @Select("select * from comment order by lit_num desc limit #{selectLimitNum}")
    List<Comment> selectAllCommentsSorted(Integer selectLimitNum);
}
