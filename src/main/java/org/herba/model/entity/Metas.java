package org.herba.model.entity;

import lombok.Data;

@Data
public class Metas {
  private Long mid;
  private String name;
  private String slug;
  private String type;
  private String description;
  private Long count;
  private Long order;
  private Long parent;


}
