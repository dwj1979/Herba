package org.herba.model.entity;

import lombok.Data;

@Data
public class Contents {

  private long cid;
  private String title;
  private String slug;
  private long created;
  private long modified;
  private String text;
  private long order;
  private long authorid;
  private String template;
  private String type;
  private String status;
  private String password;
  private long commentsnum;
  private String allowcomment;
  private String allowping;
  private String allowfeed;
  private long parent;
  private long viewsnum;
  private long views;




}
