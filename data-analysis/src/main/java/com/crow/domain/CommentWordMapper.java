package com.crow.domain;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/24.
 */
public interface CommentWordMapper {
    /*
    @Insert("insert into comment_word (`word`) values (#{word})")
    void insert(CommentWord commentWord);
    */
    //获取所有标题的分词，并按出现频率排序
    List<CommentWord> selectWordsSorted(Integer selectLimitNum);
}
