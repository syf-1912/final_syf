package com.syf.dao;

import com.syf.entity.User;
import com.syf.entity.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);

    void update(User user);

    Integer total();

    void save(@Param("users") List<User> users);

    Integer queryByDate(Integer date);

    List<UserDto> queryByProvince(String sex);
}
