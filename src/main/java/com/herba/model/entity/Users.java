package com.herba.model.entity;

import lombok.Data;

@Data
public class Users {
    private Integer uid;
    private String name;
    private String password;
    private String mail;
    private String url;
    private String screenName;
    private Integer created;
    private Integer activated;
    private Integer logged;
    private String group;
    private String authCode;

    public Users(Integer uid, String name, String password, String mail, String url, String screenname, Integer created, Integer activated, Integer logged, String group, String authcode) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.url = url;
        this.screenName = screenname;
        this.created = created;
        this.activated = activated;
        this.logged = logged;
        this.group = group;
        this.authCode = authcode;
    }

    public Users(String name) {
        this.name = name;
    }

    public Users() {
    }
}
