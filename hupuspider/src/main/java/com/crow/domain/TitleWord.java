package com.crow.domain;

/**
 * Created by CrowHawk on 17/10/11.
 */

/**
 * 帖子标题的分词结果
 */
public class TitleWord {
    private int id;
    private String word;//分词内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
