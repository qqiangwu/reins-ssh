package com.n2cj.entity;

// Generated 2015-9-6 10:20:22 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * UserToken generated by hbm2java
 */
public class UserToken implements java.io.Serializable {

    private String telephone;
    private String token;
    private Date taddtime;
    private Integer count;

    public UserToken() {
    }

    public UserToken(String telephone, String token, Date taddtime) {
        this.telephone = telephone;
        this.token = token;
        this.taddtime = taddtime;
    }

    public UserToken(String telephone, String token, Date taddtime,
                     Integer count) {
        this.telephone = telephone;
        this.token = token;
        this.taddtime = taddtime;
        this.count = count;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTaddtime() {
        return this.taddtime;
    }

    public void setTaddtime(Date taddtime) {
        this.taddtime = taddtime;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
