package com.syf.service;

import com.syf.dao.BannerDao;
import com.syf.entity.Banner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    private static Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);
    @Autowired
    private BannerDao bannerDao;

    @Override
    public void save(Banner banner) {
        bannerDao.save(banner);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Banner> queryAll(Integer page, Integer rows) {
        Integer i = (page - 1) * rows;
        List<Banner> banners = bannerDao.queryAll(i, rows);
        return banners;
    }

    @Override
    public void delete(Integer id) {
        bannerDao.delete(id);
    }

    @Override
    public void update(Banner banner) {
        bannerDao.update(banner);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer total() {
        return bannerDao.total();
    }
}
