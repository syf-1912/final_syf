package com.syf.entity;

import java.io.Serializable;

public class UserDto implements Serializable {
    private String name;
    private Integer value;

    public UserDto() {
    }

    public UserDto(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
