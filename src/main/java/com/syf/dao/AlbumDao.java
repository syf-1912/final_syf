package com.syf.dao;

import com.syf.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    void save(Album album);

    List<Album> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);

    void delete(String id);

    Integer total();

    List<Album> selectAll();

    Album queryById(String id);
}
