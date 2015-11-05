package com.n2cj.entity;

// Generated 2015-9-6 10:20:22 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * NewsFollowId generated by hbm2java
 */
public class NewsFollowId implements java.io.Serializable {

    private int userId;
    private int newsId;
    private String status;
    private Date addtime;
    private Date updatetime;

    public NewsFollowId() {
    }

    public NewsFollowId(int userId, int newsId, String status, Date addtime,
                        Date updatetime) {
        this.userId = userId;
        this.newsId = newsId;
        this.status = status;
        this.addtime = addtime;
        this.updatetime = updatetime;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewsId() {
        return this.newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAddtime() {
        return this.addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof NewsFollowId))
            return false;
        NewsFollowId castOther = (NewsFollowId) other;

        return (this.getUserId() == castOther.getUserId())
                && (this.getNewsId() == castOther.getNewsId())
                && ((this.getStatus() == castOther.getStatus()) || (this
                .getStatus() != null && castOther.getStatus() != null && this
                .getStatus().equals(castOther.getStatus())))
                && ((this.getAddtime() == castOther.getAddtime()) || (this
                .getAddtime() != null && castOther.getAddtime() != null && this
                .getAddtime().equals(castOther.getAddtime())))
                && ((this.getUpdatetime() == castOther.getUpdatetime()) || (this
                .getUpdatetime() != null
                && castOther.getUpdatetime() != null && this
                .getUpdatetime().equals(castOther.getUpdatetime())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getUserId();
        result = 37 * result + this.getNewsId();
        result = 37 * result
                + (getStatus() == null ? 0 : this.getStatus().hashCode());
        result = 37 * result
                + (getAddtime() == null ? 0 : this.getAddtime().hashCode());
        result = 37
                * result
                + (getUpdatetime() == null ? 0 : this.getUpdatetime()
                .hashCode());
        return result;
    }

}
