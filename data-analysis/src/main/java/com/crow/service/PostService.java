package com.crow.service;

import com.crow.domain.Post;
import com.crow.domain.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/21.
 */
@Service
public class PostService {
    @Autowired
    PostMapper postMapper;

    public List<Post> getAllPosts(Integer selectLimitNum) {
        return postMapper.selectAllPostsSorted(selectLimitNum);
    }
}
