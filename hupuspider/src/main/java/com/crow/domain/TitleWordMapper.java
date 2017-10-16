package com.crow.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by CrowHawk on 17/10/12.
 */
@Mapper
public interface TitleWordMapper {
    @Insert("insert into title_word (`word`) values (#{word})")
    void insert(TitleWord titleWord);
}
