package com.syf.test;

import com.syf.CmfzApp;
import com.syf.dao.BannerDao;
import com.syf.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = CmfzApp.class)
public class TestBanner {
    @Autowired
    private BannerDao bannerDao;

    @Test
    public void test1() {
        List<Banner> banners = bannerDao.queryAll(0, 2);
        for (Banner banner : banners) {
            System.out.println(banner);
        }
    }

    @Test
    public void test2() {
        Banner banner = new Banner(3, "嘻嘻嘻", "/img/captcha.jpg", "测试", "N", new Date());

        bannerDao.update(banner);
    }
}
