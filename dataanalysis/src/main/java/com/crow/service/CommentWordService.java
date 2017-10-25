package com.crow.service;

import com.crow.domain.CommentMapper;
import com.crow.domain.CommentWord;
import com.crow.domain.CommentWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrowHawk on 17/10/24.
 */
@Service
public class CommentWordService {
    @Autowired
    CommentWordMapper commentWordMapper;

    @Autowired
    CommentMapper commentMapper;

    /**
     * 根据数据库中的评论内容
     * 生成评论分词
     */
    /*
    public void insertAllWords() {
        List<Comment> commentList = commentMapper.selectAllCommentsSorted(100000);
        for(Comment comment: commentList) {
            String content = comment.getContent().replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");
            String[] strings = ToAnalysis.parse(content).toString().split(",");//分词的结果是用","分隔的
            for(String word: strings) {
                commentWordMapper.insert(new CommentWord(word));
            }
        }
    }
    */

    public List<CommentWord> getAllWords(Integer selectLimitNum) {
        List<CommentWord> commentWords = commentWordMapper.selectWordsSorted(selectLimitNum);
        List<CommentWord> commentWordList = new ArrayList<>();
        for(CommentWord commentWord: commentWords) {
            if(commentWord.getWord().matches("[\\u4e00-\\u9fa5]+/(n|a|vn|ad|b|t)")) {//去掉分词结果中的助词、语气词等
                commentWordList.add(commentWord);
            }
        }
        return commentWordList;
    }
}
