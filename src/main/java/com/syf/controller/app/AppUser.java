package com.syf.controller.app;

import com.syf.entity.User;
import com.syf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AppUser {
    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    public Map<String, String> regist(String phone, String password) {
        Map<String, String> map = new HashMap<>();
        try {
            User user = userService.regist(phone, password);
            map.put("password", user.getPassword());
            map.put("uid", user.getId());
            map.put("phone", user.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "-200");
            map.put("error_msg", e.getMessage());
        }
        return map;
    }

    @RequestMapping("/login")
    public Object login(String phone, String password) {
        try {
            User user = userService.login(phone, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> map = new HashMap<>();
            map.put("error", "-200");
            map.put("error_msg", e.getMessage());
            return map;
        }
    }
}
