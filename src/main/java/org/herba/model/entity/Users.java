package org.herba.model.entity;

import lombok.Data;

@Data
public class Users {
  private Long uid;
  private String name;
  private String password;
  private String mail;
  private String url;
  private String screenname;
  private Long created;
  private Long activated;
  private String role;
  private Long logged;
  private String group;
  private String authcode;

    public Long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getUrl() {
        return url;
    }

    public String getScreenname() {
        return screenname;
    }

    public Long getCreated() {
        return created;
    }

    public Long getActivated() {
        return activated;
    }

    public String getRole() {
        return role;
    }

    public Long getLogged() {
        return logged;
    }

    public String getGroup() {
        return group;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public void setActivated(Long activated) {
        this.activated = activated;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLogged(Long logged) {
        this.logged = logged;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }
}
