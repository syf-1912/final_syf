package com.syf.service;

import com.syf.dao.AdminDao;
import com.syf.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Admin selectOne(String name, String password) {
        return adminDao.queryByUserNameAndPassword(name, password);
    }
}
