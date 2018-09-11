package com.syf.dao;

import com.syf.entity.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> selectAll(String type);

    Article queryById(Integer id);
}
