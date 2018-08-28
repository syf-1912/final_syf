package com.syf.service;

import com.syf.entity.Admin;

public interface AdminService {
    Admin selectOne(String name, String password);
}
