package com.syf.test;

import com.syf.CmfzApp;
import com.syf.dao.MenuDao;
import com.syf.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = CmfzApp.class)
public class TestMenu {
    @Autowired
    private MenuDao menuDao;

    @Test
    public void test1() {
        List<Menu> menus = menuDao.queryAll();
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }
}
