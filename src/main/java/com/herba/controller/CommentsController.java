package com.herba.controller;

import com.herba.model.dto.DataResponse;
import com.herba.model.dto.ResponseCode;
import com.herba.model.entity.Comments;
import com.herba.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentsController {

    @Autowired
    CommentService commentService;

    /**
     * saveComment 保存评论
     */
    @RequestMapping(value = "/admin/save/comment")
    public DataResponse saveComment(@RequestBody Comments comments) {
        commentService.insertComment(comments);
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "保存评论成功", null);

    }

    /**
     * getComment 获取所有评论
     */
    @RequestMapping(value = "/admin/comments")
    public DataResponse getComment() {
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "保存评论成功", commentService.selectComment());
    }
}
