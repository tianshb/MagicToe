package com.crow.domain;

/**
 * Created by CrowHawk on 17/10/11.
 */
public class Comment {

    private int id;
    private int litNum;//评论点亮数
    private String author;
    private String content;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLitNum() {
        return litNum;
    }

    public void setLitNum(int litNum) {
        this.litNum = litNum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
