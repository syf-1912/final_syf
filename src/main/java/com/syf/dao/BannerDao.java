package com.syf.dao;

import com.syf.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    void save(Banner banner);

    List<Banner> queryAll(@Param("page") Integer page, @Param("rows") Integer rows);

    void delete(Integer id);

    void update(Banner banner);

    Integer total();

    List<Banner> selectAll();
}
