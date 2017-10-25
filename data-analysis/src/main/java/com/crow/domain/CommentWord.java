package com.crow.domain;

/**
 * Created by CrowHawk on 17/10/24.
 */
public class CommentWord {
    private Integer id;
    private String word;//分词内容
    private Integer wordCount;//分词出现次数

    /*
    public CommentWord(String word) {
        this.word = word;
    }
    */

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
