package com.study.mocktest.domain;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Getter
@Repository
public class ArticleRepository {
    private Map<Integer, Article> articles = new HashMap<>();

    public void save(Article article) {
        articles.put(articles.size(), article);
    }
}
