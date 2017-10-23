package org.herba.model.entity;

import lombok.Data;

@Data
public class Relationships {
  private Long cid;
  private Long mid;

  public Long getCid() {
    return cid;
  }

  public void setCid(Long cid) {
    this.cid = cid;
  }

  public Long getMid() {
    return mid;
  }

  public void setMid(Long mid) {
    this.mid = mid;
  }
}
