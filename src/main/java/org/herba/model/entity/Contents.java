package org.herba.model.entity;

import lombok.Data;

@Data
public class Contents {
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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(String allowComment) {
        this.allowComment = allowComment;
    }

    public String getAllowPing() {
        return allowPing;
    }

    public void setAllowPing(String allowPing) {
        this.allowPing = allowPing;
    }

    public String getAllowFeed() {
        return allowFeed;
    }

    public void setAllowFeed(String allowFeed) {
        this.allowFeed = allowFeed;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getViewsNum() {
        return viewsNum;
    }

    public void setViewsNum(Integer viewsNum) {
        this.viewsNum = viewsNum;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}