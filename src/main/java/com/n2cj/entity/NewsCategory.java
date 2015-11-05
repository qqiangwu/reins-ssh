package com.n2cj.entity;

// Generated 2015-9-6 10:20:22 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * NewsCategory generated by hbm2java
 */
public class NewsCategory implements java.io.Serializable {

    private Integer newsCategoryId;
    private String newsCategoryTitle;
    private String newsCategoryDesc;
    private int newsCategoryOrder;
    private int newsCategoryLevel;
    private Integer newsCategoryParentId;
    private boolean newsCategoryIsLeaf;
    private boolean newsCategoryIsVisible;
    private String status;
    private Date addtime;
    private Date updatetime;
    private String newsCategorySeotitle;
    private String newsCategoryKeywords;
    private String newsCategorySavePath;
    private String newsCategoryHotTags;
    private Set newsCategoryRels = new HashSet(0);

    public NewsCategory() {
    }

    public NewsCategory(String newsCategoryTitle, int newsCategoryOrder,
                        int newsCategoryLevel, boolean newsCategoryIsLeaf,
                        boolean newsCategoryIsVisible, String status, Date addtime,
                        Date updatetime) {
        this.newsCategoryTitle = newsCategoryTitle;
        this.newsCategoryOrder = newsCategoryOrder;
        this.newsCategoryLevel = newsCategoryLevel;
        this.newsCategoryIsLeaf = newsCategoryIsLeaf;
        this.newsCategoryIsVisible = newsCategoryIsVisible;
        this.status = status;
        this.addtime = addtime;
        this.updatetime = updatetime;
    }

    public NewsCategory(String newsCategoryTitle, String newsCategoryDesc,
                        int newsCategoryOrder, int newsCategoryLevel,
                        Integer newsCategoryParentId, boolean newsCategoryIsLeaf,
                        boolean newsCategoryIsVisible, String status, Date addtime,
                        Date updatetime, String newsCategorySeotitle,
                        String newsCategoryKeywords, String newsCategorySavePath,
                        String newsCategoryHotTags, Set newsCategoryRels) {
        this.newsCategoryTitle = newsCategoryTitle;
        this.newsCategoryDesc = newsCategoryDesc;
        this.newsCategoryOrder = newsCategoryOrder;
        this.newsCategoryLevel = newsCategoryLevel;
        this.newsCategoryParentId = newsCategoryParentId;
        this.newsCategoryIsLeaf = newsCategoryIsLeaf;
        this.newsCategoryIsVisible = newsCategoryIsVisible;
        this.status = status;
        this.addtime = addtime;
        this.updatetime = updatetime;
        this.newsCategorySeotitle = newsCategorySeotitle;
        this.newsCategoryKeywords = newsCategoryKeywords;
        this.newsCategorySavePath = newsCategorySavePath;
        this.newsCategoryHotTags = newsCategoryHotTags;
        this.newsCategoryRels = newsCategoryRels;
    }

    public Integer getNewsCategoryId() {
        return this.newsCategoryId;
    }

    public void setNewsCategoryId(Integer newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    public String getNewsCategoryTitle() {
        return this.newsCategoryTitle;
    }

    public void setNewsCategoryTitle(String newsCategoryTitle) {
        this.newsCategoryTitle = newsCategoryTitle;
    }

    public String getNewsCategoryDesc() {
        return this.newsCategoryDesc;
    }

    public void setNewsCategoryDesc(String newsCategoryDesc) {
        this.newsCategoryDesc = newsCategoryDesc;
    }

    public int getNewsCategoryOrder() {
        return this.newsCategoryOrder;
    }

    public void setNewsCategoryOrder(int newsCategoryOrder) {
        this.newsCategoryOrder = newsCategoryOrder;
    }

    public int getNewsCategoryLevel() {
        return this.newsCategoryLevel;
    }

    public void setNewsCategoryLevel(int newsCategoryLevel) {
        this.newsCategoryLevel = newsCategoryLevel;
    }

    public Integer getNewsCategoryParentId() {
        return this.newsCategoryParentId;
    }

    public void setNewsCategoryParentId(Integer newsCategoryParentId) {
        this.newsCategoryParentId = newsCategoryParentId;
    }

    public boolean isNewsCategoryIsLeaf() {
        return this.newsCategoryIsLeaf;
    }

    public void setNewsCategoryIsLeaf(boolean newsCategoryIsLeaf) {
        this.newsCategoryIsLeaf = newsCategoryIsLeaf;
    }

    public boolean isNewsCategoryIsVisible() {
        return this.newsCategoryIsVisible;
    }

    public void setNewsCategoryIsVisible(boolean newsCategoryIsVisible) {
        this.newsCategoryIsVisible = newsCategoryIsVisible;
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

    public String getNewsCategorySeotitle() {
        return this.newsCategorySeotitle;
    }

    public void setNewsCategorySeotitle(String newsCategorySeotitle) {
        this.newsCategorySeotitle = newsCategorySeotitle;
    }

    public String getNewsCategoryKeywords() {
        return this.newsCategoryKeywords;
    }

    public void setNewsCategoryKeywords(String newsCategoryKeywords) {
        this.newsCategoryKeywords = newsCategoryKeywords;
    }

    public String getNewsCategorySavePath() {
        return this.newsCategorySavePath;
    }

    public void setNewsCategorySavePath(String newsCategorySavePath) {
        this.newsCategorySavePath = newsCategorySavePath;
    }

    public String getNewsCategoryHotTags() {
        return this.newsCategoryHotTags;
    }

    public void setNewsCategoryHotTags(String newsCategoryHotTags) {
        this.newsCategoryHotTags = newsCategoryHotTags;
    }

    public Set getNewsCategoryRels() {
        return this.newsCategoryRels;
    }

    public void setNewsCategoryRels(Set newsCategoryRels) {
        this.newsCategoryRels = newsCategoryRels;
    }

}
