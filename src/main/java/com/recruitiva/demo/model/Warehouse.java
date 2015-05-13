package com.recruitiva.demo.model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.recruitiva.demo.entity.Article;
import com.recruitiva.demo.entity.Category;

@Transactional
public interface Warehouse {
    List<Category> categories();

    Category saveCategory(Category category);

    List<Article> articles();

    List<Article> articles(Long categoryId);

    Article saveArticle(Article article);
}
