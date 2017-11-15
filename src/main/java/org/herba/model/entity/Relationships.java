package org.herba.model.entity;

import lombok.Data;

@Data
public class Relationships {
    private Integer cid;

    private Integer mid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Relationships() {
    }

    public Relationships(Integer cid, Integer mid) {
        this.cid = cid;
        this.mid = mid;
    }
}