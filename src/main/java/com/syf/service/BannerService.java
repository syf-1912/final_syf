package com.syf.service;

import com.syf.entity.Banner;

import java.util.List;

public interface BannerService {
    void save(Banner banner);

    List<Banner> queryAll(Integer page, Integer rows);

    void delete(Integer id);

    void update(Banner banner);

    Integer total();
}
