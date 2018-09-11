package com.syf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 */

public class Article implements Serializable {
    private Integer id;
    private String name;//名称
    private String insertImg;//插图
    private String content;//内容
    private String status;//状态
    private Integer guruId;//上师ID
    private Date publicshDate;//发布日期
    private Date createDate;//创建日期

    public Article() {
    }

    public Article(Integer id, String name, String insertImg, String content, String status, Integer guruId, Date publicshDate, Date createDate) {
        this.id = id;
        this.name = name;
        this.insertImg = insertImg;
        this.content = content;
        this.status = status;
        this.guruId = guruId;
        this.publicshDate = publicshDate;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsertImg() {
        return insertImg;
    }

    public void setInsertImg(String insertImg) {
        this.insertImg = insertImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getGuruId() {
        return guruId;
    }

    public void setGuruId(Integer guruId) {
        this.guruId = guruId;
    }

    public Date getPublicshDate() {
        return publicshDate;
    }

    public void setPublicshDate(Date publicshDate) {
        this.publicshDate = publicshDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", insertImg='" + insertImg + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", guruId=" + guruId +
                ", publicshDate=" + publicshDate +
                ", createDate=" + createDate +
                '}';
    }
}
