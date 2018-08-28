package com.syf.controller;

import com.syf.entity.Admin;
import com.syf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) {
        Admin admin = adminService.selectOne(username, password);
        session.setAttribute("admin", admin);
        return "index";
    }

}
