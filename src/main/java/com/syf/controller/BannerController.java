package com.syf.controller;

import com.syf.entity.Banner;
import com.syf.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/showAll")
    public Map<String, Object> showAll(Integer page, Integer rows) {
        //System.out.println("page:"+page+"  rows:"+rows);
        Map<String, Object> map = new HashMap<String, Object>();
        //求总条数
        Integer total = bannerService.total();
        //获得当前页的数据
        List<Banner> banners = bannerService.queryAll(page, rows);
        map.put("total", total);
        map.put("rows", banners);
        return map;
    }

    @RequestMapping("/add")
    public void add(Banner banner) {
        bannerService.save(banner);
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        bannerService.delete(id);
    }

    @RequestMapping("/update")
    public void update(Banner banner) {
        bannerService.update(banner);
    }
}
