package com.crow.domain;

import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/12.
 */

public interface TitleWordMapper {
    @Insert("insert into title_word (`word`) values (#{word})")
    void insert(TitleWord titleWord);
    //获取所有标题的分词，并按出现频率排序
    List<TitleWord> selectWordsSorted(Integer selectLimitNum);
}
