package com.study.common.pojo.xxxdo;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Table(name = "userinfo")
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid ;

    @Column(name = "username")
    private String username;

    @Column(name = "userlike")
    private String userlike;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserlike() {
        return userlike;
    }

    public void setUserlike(String userlike) {
        this.userlike = userlike;
    }
}

