package com.n2cj.entity;

import java.util.Date;

// Generated 2015-10-15 14:24:38 by Hibernate Tools 4.3.1

/**
 * TAnaly generated by hbm2java
 */
public class TAnaly implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7724633913497414985L;
    private String id;
    private String wareCode;
    private String anaS1;
    private String anaS2;
    private String anaS3;
    private String anaR1;
    private String anaR2;
    private String anaR3;
    private String s1Isvalid;
    private String s2Isvalid;
    private String s3Isvalid;
    private String r1Isvalid;
    private String r2Isvalid;
    private String r3Isvalid;
    private String anaIsvalid;
    private String anaPath;
    private String order;
    private Date createDate;
    private Date editDate;
    private String anaType;

    public TAnaly() {
    }

    public TAnaly(String id, String wareCode, String anaS1, String anaS2,
            String anaS3, String anaR1, String anaR2, String anaR3,
            String s1Isvalid, String s2Isvalid, String s3Isvalid,
            String r1Isvalid, String r2Isvalid, String r3Isvalid,
            String anaIsvalid, String anaPath, String order, Date createDate,
            Date editDate, String anaType) {
        this.id = id;
        this.wareCode = wareCode;
        this.anaS1 = anaS1;
        this.anaS2 = anaS2;
        this.anaS3 = anaS3;
        this.anaR1 = anaR1;
        this.anaR2 = anaR2;
        this.anaR3 = anaR3;
        this.s1Isvalid = s1Isvalid;
        this.s2Isvalid = s2Isvalid;
        this.s3Isvalid = s3Isvalid;
        this.r1Isvalid = r1Isvalid;
        this.r2Isvalid = r2Isvalid;
        this.r3Isvalid = r3Isvalid;
        this.anaIsvalid = anaIsvalid;
        this.anaPath = anaPath;
        this.order = order;
        this.createDate = createDate;
        this.editDate = editDate;
        this.anaType = anaType;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWareCode() {
        return this.wareCode;
    }

    public void setWareCode(String wareCode) {
        this.wareCode = wareCode;
    }

    public String getAnaS1() {
        return this.anaS1;
    }

    public void setAnaS1(String anaS1) {
        this.anaS1 = anaS1;
    }

    public String getAnaS2() {
        return this.anaS2;
    }

    public void setAnaS2(String anaS2) {
        this.anaS2 = anaS2;
    }

    public String getAnaS3() {
        return this.anaS3;
    }

    public void setAnaS3(String anaS3) {
        this.anaS3 = anaS3;
    }

    public String getAnaR1() {
        return this.anaR1;
    }

    public void setAnaR1(String anaR1) {
        this.anaR1 = anaR1;
    }

    public String getAnaR2() {
        return this.anaR2;
    }

    public void setAnaR2(String anaR2) {
        this.anaR2 = anaR2;
    }

    public String getAnaR3() {
        return this.anaR3;
    }

    public void setAnaR3(String anaR3) {
        this.anaR3 = anaR3;
    }

    public String getS1Isvalid() {
        return this.s1Isvalid;
    }

    public void setS1Isvalid(String s1Isvalid) {
        this.s1Isvalid = s1Isvalid;
    }

    public String getS2Isvalid() {
        return this.s2Isvalid;
    }

    public void setS2Isvalid(String s2Isvalid) {
        this.s2Isvalid = s2Isvalid;
    }

    public String getS3Isvalid() {
        return this.s3Isvalid;
    }

    public void setS3Isvalid(String s3Isvalid) {
        this.s3Isvalid = s3Isvalid;
    }

    public String getR1Isvalid() {
        return this.r1Isvalid;
    }

    public void setR1Isvalid(String r1Isvalid) {
        this.r1Isvalid = r1Isvalid;
    }

    public String getR2Isvalid() {
        return this.r2Isvalid;
    }

    public void setR2Isvalid(String r2Isvalid) {
        this.r2Isvalid = r2Isvalid;
    }

    public String getR3Isvalid() {
        return this.r3Isvalid;
    }

    public void setR3Isvalid(String r3Isvalid) {
        this.r3Isvalid = r3Isvalid;
    }

    public String getAnaIsvalid() {
        return this.anaIsvalid;
    }

    public void setAnaIsvalid(String anaIsvalid) {
        this.anaIsvalid = anaIsvalid;
    }

    public String getAnaPath() {
        return this.anaPath;
    }

    public void setAnaPath(String anaPath) {
        this.anaPath = anaPath;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEditDate() {
        return this.editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getAnaType() {
        return this.anaType;
    }

    public void setAnaType(String anaType) {
        this.anaType = anaType;
    }

}
