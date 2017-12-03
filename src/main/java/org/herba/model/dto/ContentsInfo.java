package org.herba.model.dto;

import java.util.List;

import org.herba.model.entity.Comments;
import org.herba.model.entity.Metas;

import lombok.Data;
import org.herba.model.entity.Users;

/**
 * ContentsInfo 后台分页文章或则页面的信息，无全部标签和分类信息
 *
 * @author Varshonwood
 * @version 1.0
 * @date 17/11/11
 */
@Data
public class ContentsInfo {
    private Integer cid;
    private String title;
    private String slug;
    private Integer created;
    private Integer modified;
    private Integer order;
    private Integer authorId;
    private String template;
    private String type;
    private String status;
    private String password;
    private Integer commentsNum;
    private String allowComment;
    private String allowPing;
    private String allowFeed;
    private Integer parent;
    private Integer viewsNum;
    private Integer views;
    private String text;
    /**
     * metas 和文章有关联的所有标签和分类
     */
    private List<Metas> metas;
    /**
     * users 文章的作者信息
     */
    private Users users;
    /**
     * comments 和文章有关联的评论信息
     */
    private List<Comments> comments;

    /**
     * ContentsInfo
     */
    public ContentsInfo() {
    }
}
//~ Formatted by Jindent --- http://www.jindent.com
