package com.syf.service;

import com.syf.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AlbumService {
    void save(Album album, MultipartFile img, HttpServletRequest request);

    List<Album> queryAll(Integer page, Integer rows);

    void delete(String id);

    Integer total();

    List<Album> selectAll();

    Album queryOne(String id);
}
