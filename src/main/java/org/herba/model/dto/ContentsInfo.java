package org.herba.model.dto;

import lombok.Data;
import org.herba.model.entity.Metas;
import org.herba.model.entity.Users;

import java.util.List;

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

    private List<Metas> metas;
    private Users users;

}
