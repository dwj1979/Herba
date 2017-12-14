package org.herba.service;

import org.herba.model.entity.Comments;
import org.herba.model.mapper.CommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentsMapper commentsMapper;

    /**
     * insertComment 插入评论
     *
     * @param comments 评论实体
     */
    public void insertComment(Comments comments) {
        commentsMapper.insertSelective(comments);
    }
}
