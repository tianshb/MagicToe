package com.crow.domain;

/**
 * Created by CrowHawk on 17/10/6.
 */
//@TargetUrl("https://bbs.hupu.com/\\d+.html")
//@HelpUrl("https://bbs.hupu.com/bxj")

//@Table(name = "PostInfo")
public class HupuBxjPostInfo {

    //@Id
    //@GeneratedValue
    private int id;
    //    @ExtractBy("//div[@class='bbs-hd-h1']/text()")
    private String title;

    //    @ExtractBy("//div[@class='floor']/div[@class='floor-show']//@uname")
    private String author;

    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
