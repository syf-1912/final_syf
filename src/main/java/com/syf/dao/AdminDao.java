package com.syf.dao;

import com.syf.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    Admin queryByUserNameAndPassword(@Param("username") String username,
                                     @Param("password") String password);
}
