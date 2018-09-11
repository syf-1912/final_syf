package com.syf.controller.app;

import com.syf.entity.Album;
import com.syf.entity.Article;
import com.syf.service.AlbumService;
import com.syf.service.ArticleService;
import com.syf.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务端接口暴露
 */
@RestController
@RequestMapping("/app")
public class AppMain {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;

    /**
     * 首页
     *
     * @param uid     用户id  必填
     * @param type    请求数据类型(all、wen、si)  必填
     * @param subType 上师言教(ssyj)  显密法要(xmfy) 当type为‘si’时填入
     */
    @RequestMapping("/first_page")
    public Map<String, Object> firstPage(String uid, String type, String subType) {
        Map<String, Object> map = new HashMap<>();

        //当type为all时
        if ("all".equals(type)) {
            //获取轮播图数据
            map.put("header", bannerService.selectAll());
            //获取专辑数据
            map.put("album", albumService.selectAll());
            //获取文章数据
            map.put("article", articleService.selectAll("ssyj"));
            //当type为wen时
        } else if ("wen".equals(type)) {
            map.put("album", albumService.selectAll());
            //当type为si时
        } else if ("si".equals(type)) {
            if ("xmfy".equals(subType)) {
                map.put("article", articleService.selectAll(subType));
            } else {
                map.put("article", articleService.selectAll("ssyj"));
            }
        } else {
            map.put("error", "请求参数有误，请重试！");
        }
        return map;
    }

    /**
     * 专辑详情页接口
     *
     * @param id  专辑id
     * @param uid 用户id
     */
    @RequestMapping("/wen")
    public Album wen(String id, String uid) {
        Album album = albumService.queryOne(id);
        return album;
    }

    /**
     * 文章详情页接口
     *
     * @param id  文章id
     * @param uid 用户id
     */
    @RequestMapping("/si")
    public Map<String, Object> si(Integer id, String uid) {
        Map<String, Object> map = new HashMap<>();
        Article article = articleService.queryOne(id);
        return map;
    }
}
