package org.herba.controller;

import org.herba.model.entity.Comments;
import org.herba.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/admin/save/comment")
    public void saveComment(@RequestBody Comments comments) {
        commentService.insertComment(comments);
    }

    @RequestMapping(value = "admin/comments")
    public List<Comments> getComment() {
        return commentService.selectComment();
    }
}
