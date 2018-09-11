package com.syf.service;

import com.syf.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> selectAll(String type);

    Article queryOne(Integer id);
}
