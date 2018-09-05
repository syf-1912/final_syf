package com.syf.service;

import com.syf.entity.User;
import com.syf.entity.UserDto;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<User> queryAll(Integer page, Integer rows);

    void update(User user);

    Integer total();

    Workbook exportAll(Integer page, Integer rows);

    Workbook customerExport(String titles, String params, Integer page, Integer rows);

    void importData(MultipartFile file);

    Integer queryByDate(Integer date);

    List<UserDto> queryByProvince(String sex);
}
