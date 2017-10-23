package org.herba.model.entity;

import lombok.Data;

@Data
public class Comments {
  private Long coid;
  private Long cid;
  private Long created;
  private String author;
  private Long authorid;
  private Long ownerid;
  private String mail;
  private String url;
  private String ip;
  private String agent;
  private String text;
  private String type;
  private String status;
  private Long parent;


}
