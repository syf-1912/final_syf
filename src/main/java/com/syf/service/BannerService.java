package com.syf.service;

import com.syf.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BannerService {
    void save(Banner banner, MultipartFile img, HttpServletRequest request);

    List<Banner> queryAll(Integer page, Integer rows);

    void delete(Integer id);

    void update(Banner banner);

    Integer total();

    List<Banner> selectAll();
}
