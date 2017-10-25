package com.crow.webmagic.pipeline;

import com.crow.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * Created by CrowHawk on 17/10/6.
 */
@Component("PostInfoPipeline")
public class HupuSpiderPipeline implements Pipeline{


    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TitleWordMapper titleWordMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {

        for(Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {

            if(entry.getKey().equals("postInfo")) {
                Post post = (Post) entry.getValue();
                if(post != null) {
                    postMapper.insert(post);
                }
            }
            if(entry.getKey().equals("commentInfo")) {
                CommentList commentList = (CommentList) entry.getValue();
                for(int i = 0; i < commentList.getContentList().size(); i++) {
                    Comment comment = new Comment();
                    comment.setTitle(commentList.getTitle());
                    comment.setContent(commentList.getContentList().get(i).replaceAll("(& nbsp;)", ""));
                    comment.setLitNum(Integer.parseInt(commentList.getLitNumList().get(i)));
                    comment.setAuthor(commentList.getCommentAuthors().get(i));
                    commentMapper.insert(comment);
                }
            }
            if(entry.getKey().equals("titleWordInfo")) {
                String[] strings = (String[])entry.getValue();
                for(String word: strings) {
                    TitleWord titleWord = new TitleWord();
                    titleWord.setWord(word);
                    titleWordMapper.insert(titleWord);
                }
            }
            if(entry.getKey().equals("userInfo")) {
                User user = (User) entry.getValue();
                if(user != null) {
                    userMapper.insert(user);
                }
            }
        }

    }
}
