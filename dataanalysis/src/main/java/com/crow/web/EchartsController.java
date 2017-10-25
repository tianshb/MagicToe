package com.crow.web;

import com.crow.domain.AuxiliaryModels.NameValue;
import com.crow.domain.CommentWord;
import com.crow.domain.Post;
import com.crow.domain.TitleWord;
import com.crow.domain.User;
import com.crow.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrowHawk on 17/10/21.
 */
@Controller
public class EchartsController {
    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;
    @Autowired
    TitleWordService titleWordService;
    @Autowired
    UserService userService;
    @Autowired
    CommentWordService commentWordService;

    @RequestMapping(value = "/getAddress")
    @ResponseBody
    public List<User> getAddress() {
        List<User> users = userService.getAddresses(18);
        return users;
    }

    @RequestMapping(value = "/getGender")
    @ResponseBody
    public List<NameValue> getGender() {
        List<User> users = userService.getGender();
        List<NameValue> result = new ArrayList<>();
        for(User user: users) {
            result.add(new NameValue(user.getGenderNum(), user.getGender()));
        }
        return result;
    }

    @RequestMapping(value = "/getTitleWord")
    @ResponseBody
    public List<NameValue> getTitleWord() {
        List<TitleWord> titleWords = titleWordService.getAllWords(30);
        List<NameValue> result = new ArrayList<>();
        for(TitleWord titleWord: titleWords) {
            result.add(new NameValue(titleWord.getWordCount(), titleWord.getWord()));
        }
        return result;
    }

    @RequestMapping(value = "/getTitle")
    @ResponseBody
    public List<NameValue> getTitle() {
        List<Post> posts = postService.getAllPosts(20);
        List<NameValue> result = new ArrayList<>();
        for(Post post: posts) {
            result.add(new NameValue(post.getReplyNum(), post.getTitle()));
        }
        return result;
    }

    @RequestMapping(value = "/getViews")
    @ResponseBody
    public List<User> getViews() {
        List<User> users = userService.getAllUsers(10);
        return users;
    }

    @RequestMapping(value = "/getCommentWord")
    @ResponseBody
    public List<NameValue> getCommentWord() {
        List<CommentWord> commentWords = commentWordService.getAllWords(300);
        List<NameValue> result = new ArrayList<>();
        for(CommentWord commentWord: commentWords) {
            String word = commentWord.getWord();
            result.add(new NameValue(commentWord.getWordCount(), word.substring(0, word.indexOf("/"))));//去掉分词中的词性标识
        }
        return result;
    }

    /*
    @RequestMapping(value = "/genCommentWord")
    public void genCommentWord() {
        commentWordService.insertAllWords();
    }
    */

    @RequestMapping(value = "/province")
    public String getAddressEcharts() {
        return "ProvinceAddress";
    }

    @RequestMapping(value = "/gender")
    public String getGenderEcharts() {
        return "Gender";
    }

    @RequestMapping(value = "/views")
    public String getViewsEcharts() {
        return "Views";
    }

    @RequestMapping(value = "/titleword")
    public String getTitleWordEcharts() {
        return "TitleWords";
    }

    @RequestMapping(value = "/title")
    public String getTitleEcharts() {
        return "Titles";
    }

    @RequestMapping(value = "/commentword")
    public String getCommentWordEcharts() {
        return "CommentWords";
    }
}
