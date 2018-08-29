package com.syf.controller;

import com.syf.entity.Menu;
import com.syf.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/showAll")
    public List<Menu> showAll() {
        List<Menu> menus = menuService.queryAll();
        return menus;
    }
}
