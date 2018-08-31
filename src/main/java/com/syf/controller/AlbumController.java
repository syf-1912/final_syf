package com.syf.controller;

import com.syf.entity.Album;
import com.syf.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/showAll")
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        //求总条数
        Integer total = albumService.total();
        //获得当前页的数据
        List<Album> albums = albumService.queryAll(page, rows);
        map.put("total", total);
        map.put("rows", albums);
        return map;
    }

    @RequestMapping("/add")
    public void add(Album album, MultipartFile img, HttpServletRequest request) {
        albumService.save(album, img, request);
    }

    @RequestMapping("/delete")
    public void delete(String id) {
        albumService.delete(id);
    }
}