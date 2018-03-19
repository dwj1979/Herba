package com.herba.service;

import com.herba.model.dto.ContentsInfo;
import com.herba.model.entity.Comments;
import com.herba.model.entity.Contents;
import com.herba.model.mapper.CommentsMapper;
import com.herba.model.mapper.ContentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    ContentsMapper contentsMapper;

    /**
     * insertComment 插入评论
     *
     * @param comments 评论实体
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertComment(Comments comments) {
        commentsMapper.insertSelective(comments);
        ContentsInfo contentsInfo = contentsMapper.selectPostByPrimaryKey(comments.getCid());
        int commentsNum = contentsInfo.getCommentsNum();
        Contents contents = new Contents();
        contents.setCid(comments.getCid());
        contents.setCommentsNum(++commentsNum);
        contentsMapper.updateByPrimaryKeySelective(contents);
    }

    /**
     * selectComment 获取全量评论
     */
    @Transactional(rollbackFor = Exception.class)
    public List<Comments> selectComment() {
        return commentsMapper.selectComment();
    }
}
