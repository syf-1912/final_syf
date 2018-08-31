package com.syf.dao;

import com.syf.entity.Chapter;

import java.util.List;

public interface ChapterDao {
    List<Chapter> queryByPid(String id);

    void delete(Integer id);

    void save(Chapter chapter);
}
