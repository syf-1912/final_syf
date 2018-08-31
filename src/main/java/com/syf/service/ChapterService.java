package com.syf.service;

import com.syf.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ChapterService {
    List<Chapter> queryByPid(String id);

    void delete(Integer id);

    void save(Chapter chapter, MultipartFile audio, HttpServletRequest request);
}
