package com.syf.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String id;
    private String name;//姓名
    private String dharmaName;//法名
    private String password;//密码
    private String sex;//性别
    private String province;//省份
    private String city;//城市
    private String phone;//手机
    private String sign;//签名
    private String photoImg;//头像
    private String salt;//私盐
    private String status;//状态
    private Date registDate;//注册时间

    public User() {
    }

    public User(String id, String name, String dharmaName, String password, String sex, String province, String city, String phone, String sign, String photoImg, String salt, String status, Date registDate) {
        this.id = id;
        this.name = name;
        this.dharmaName = dharmaName;
        this.password = password;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.phone = phone;
        this.sign = sign;
        this.photoImg = photoImg;
        this.salt = salt;
        this.status = status;
        this.registDate = registDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", sign='" + sign + '\'' +
                ", photoImg='" + photoImg + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                ", registDate=" + registDate +
                '}';
    }
}
