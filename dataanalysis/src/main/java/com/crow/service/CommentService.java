package com.crow.service;

import com.crow.domain.Comment;
import com.crow.domain.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/21.
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    public List<Comment> getAllComments(Integer selectLimitNum) {
        return commentMapper.selectAllCommentsSorted(selectLimitNum);
    }
}
