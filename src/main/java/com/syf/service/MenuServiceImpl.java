package com.syf.service;

import com.syf.dao.MenuDao;
import com.syf.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Menu> queryAll() {
        List<Menu> menus = menuDao.queryAll();

        return menus;
    }
}
