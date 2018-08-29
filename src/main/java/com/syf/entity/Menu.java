package com.syf.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
    private Integer id;
    private String title;//标题
    private String href;//链接地址
    private String iconCls;//图标
    private Integer parntId;//父id
    private List<Menu> childrens;//二级分类

    public Menu(Integer id, String title, String href, String iconCls, Integer parntId, List<Menu> childrens) {
        this.id = id;
        this.title = title;
        this.href = href;
        this.iconCls = iconCls;
        this.parntId = parntId;
        this.childrens = childrens;
    }

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", parntId=" + parntId +
                ", childrens=" + childrens +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getParntId() {
        return parntId;
    }

    public void setParntId(Integer parntId) {
        this.parntId = parntId;
    }

    public List<Menu> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Menu> childrens) {
        this.childrens = childrens;
    }
}
