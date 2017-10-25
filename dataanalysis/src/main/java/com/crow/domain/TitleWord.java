package com.crow.domain;

/**
 * Created by CrowHawk on 17/10/11.
 */

/**
 * 帖子标题的分词结果
 */
public class TitleWord {
    private Integer id;
    private String word;//分词内容

    private Integer wordCount;//分词出现次数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }
}
