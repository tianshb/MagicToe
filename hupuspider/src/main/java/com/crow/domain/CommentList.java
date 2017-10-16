package com.crow.domain;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/14.
 */
public class CommentList {
    private String title;
    private List<String> contentList;
    private List<String> litNumList;
    private List<String> commentAuthors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    public List<String> getLitNumList() {
        return litNumList;
    }

    public void setLitNumList(List<String> litNumList) {
        this.litNumList = litNumList;
    }

    public List<String> getCommentAuthors() {
        return commentAuthors;
    }

    public void setCommentAuthors(List<String> commentAuthors) {
        this.commentAuthors = commentAuthors;
    }
}
